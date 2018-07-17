package cn.law.im.client.main

import android.content.Intent
import cn.law.im.client.R
import cn.law.im.client.main.chat.ChatActivity
import cn.law.im.client.protocal.AppBaseCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppBaseCompatActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initVariables() {
    }

    override fun initListeners() {
        mBtnChat.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChatActivity::class.java))
        }
    }

    override fun init() {
    }

    override fun destroyTask() {
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
