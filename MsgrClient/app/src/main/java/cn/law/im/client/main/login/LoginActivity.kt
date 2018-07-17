package cn.law.im.client.main.login

import android.content.Intent
import cn.law.im.client.R
import cn.law.im.client.main.MainActivity
import cn.law.im.client.protocal.AppBaseCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppBaseCompatActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initVariables() {
    }

    override fun initListeners() {
        mBtnLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun init() {
    }

    override fun destroyTask() {
    }
}