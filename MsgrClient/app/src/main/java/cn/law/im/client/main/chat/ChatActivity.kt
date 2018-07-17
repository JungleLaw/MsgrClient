package cn.law.im.client.main.chat

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import cn.law.im.client.R
import cn.law.im.client.jni.JNI
import cn.law.im.client.main.chat.entity.Msg
import cn.law.im.client.main.chat.adapter.MsgAdapter
import cn.law.im.client.protocal.AppBaseCompatActivity
import java.io.*
import java.net.Socket

class ChatActivity : AppBaseCompatActivity() {

    private var mMsgThread: Thread? = null

    private var soc: Socket? = null
    private var input: BufferedReader? = null
    private var output: PrintWriter? = null
    private var mBtnSend: Button? = null
    private var mEtMsg: EditText? = null

    companion object {
        private val mMsgs: ArrayList<Msg> = ArrayList()
        private var mAdapter: MsgAdapter? = null
        private var mLvMsg: ListView? = null

        private var mHandler: Handler? = object : Handler() {
            override fun handleMessage(msg: android.os.Message?) {
//                super.handleMessage(msg)
                val message = Msg(msg!!.data!!.getString("msg"))
                mMsgs.add(message)
                mAdapter!!.notifyDataSetChanged()
                mLvMsg!!.setSelection(mMsgs.size)
            }
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun initVariables() {
    }

    override fun initListeners() {
        mLvMsg = findViewById(R.id.mLvMsg)
        mBtnSend = findViewById(R.id.mBtnSend)
        mEtMsg = findViewById(R.id.mEtMsg)

        mBtnSend!!.setOnClickListener {
            if (!TextUtils.isEmpty(mEtMsg!!.text)) {
                val msg = mEtMsg!!.text.toString()
//                mMsgs.add(Msg())
//                mAdapter!!.notifyDataSetChanged()
//                mLvMsg!!.setSelection(mMsgs.size)
//                mEtMsg!!.setText("")
                mEtMsg!!.setText("")
                Thread(Runnable {
                    if (output != null) {
                        output!!.write(msg + "\n")
                        output!!.flush()
                    }
                }).start()
            }
        }
    }

    override fun init() {
        for (index in 1..20) {
            val msg = Msg("Msg $index")
            mMsgs.add(msg)
        }

        mAdapter = MsgAdapter(this, mMsgs)
        mLvMsg!!.adapter = mAdapter
        mLvMsg!!.setSelection(mMsgs.size - 1)

        connect()
    }

    override fun destroyTask() {
        close()
    }


    private fun close() {
        if (soc != null) {
            if (!soc!!.isInputShutdown)
                soc!!.shutdownInput()
            if (!soc!!.isOutputShutdown)
                soc!!.shutdownOutput()
            if (!soc!!.isClosed)
                soc!!.close()
            soc = null
        }

        if (input != null) {
            input!!.close()
            input = null
        }

        if (output != null) {
            output!!.close()
            output = null
        }
    }

    private fun connect() {
        mMsgThread = Thread(Runnable {
            try {
                Log.i("TAG", JNI.getServerUrl() + ":" + JNI.getServerPort())
                soc = Socket(JNI.getServerUrl(), JNI.getServerPort())
//                soc = Socket("192.168.1.106", 8080)
                //接收消息的流对象
                input = BufferedReader(InputStreamReader(soc!!.getInputStream()))
                //发送消息的流对象
                output = PrintWriter(BufferedWriter(OutputStreamWriter(soc!!.getOutputStream())))
                while (true) {
                    if (soc != null && !soc!!.isClosed && soc!!.isConnected && !soc!!.isInputShutdown) {
                        val line = input!!.readLine()
                        if (!TextUtils.isEmpty(line)) {
                            val msg = mHandler!!.obtainMessage()
                            val data = Bundle()
                            data.putString("msg", line)
                            msg.data = data
                            mHandler!!.sendMessage(msg)
                        }
                    }
                }
            } catch (e: IOException) {
            }
        })
        mMsgThread!!.start()
    }
}