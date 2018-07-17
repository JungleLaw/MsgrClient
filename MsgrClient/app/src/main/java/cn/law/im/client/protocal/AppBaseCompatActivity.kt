package cn.law.im.client.protocal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class AppBaseCompatActivity : AppCompatActivity() {
    abstract fun setLayoutId(): Int

    abstract fun initVariables()

    abstract fun initListeners()

    abstract fun init()

    abstract fun destroyTask()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (setLayoutId() <= 0) {
            throw IllegalArgumentException("wrong layout id!")
        } else {
            setContentView(setLayoutId())
        }

        initVariables()
        initListeners()
        init()
    }


    override fun onDestroy() {
        super.onDestroy()
        destroyTask()
    }
}