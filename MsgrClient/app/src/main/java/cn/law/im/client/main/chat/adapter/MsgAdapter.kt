package cn.law.im.client.main.chat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cn.law.im.client.R
import cn.law.im.client.main.chat.entity.Msg

class MsgAdapter : BaseAdapter {
    private var mContext: Context? = null
    private var mMsgs: List<Msg>? = ArrayList()

    constructor(mContext: Context?, mMsgs: List<Msg>?) : super() {
        this.mContext = mContext
        this.mMsgs = mMsgs
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val msg = mMsgs!![position]
        val itemView: View?
        val holder: Holder?
        if (convertView == null) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_msg, parent, false)
            holder = Holder(itemView)
            itemView.tag = holder
        } else {
            itemView = convertView
            holder = itemView.tag as Holder?
        }

        holder!!.mTvDisplay!!.text = msg.content

        return itemView!!
    }

    override fun getItem(position: Int): Any? {
        return if (mMsgs == null) null else mMsgs!![position]
    }

    override fun getItemId(position: Int): Long {
        return if (mMsgs == null) 0 else position.toLong()
    }

    override fun getCount(): Int {
        return if (mMsgs == null) 0 else mMsgs!!.size
    }

    private class Holder(itemView: View) {
        var mTvDisplay: TextView? = null

        init {
            mTvDisplay = itemView.findViewById(R.id.mTvDisplay) as TextView
        }
    }
}