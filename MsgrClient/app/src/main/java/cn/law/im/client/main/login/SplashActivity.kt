package cn.law.im.client.main.login

import android.content.Intent
import android.os.Handler
import cn.law.im.client.R
import cn.law.im.client.Constants
import cn.law.im.client.protocal.AppBaseCompatActivity
import cn.law.im.client.utils.PrefrencesUtils

class SplashActivity : AppBaseCompatActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initVariables() {
    }

    override fun initListeners() {
    }

    override fun init() {
        Handler().postDelayed({
            PrefrencesUtils.put(Constants.Prefs.Login.FIRST, true)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, 3000)
    }

    override fun destroyTask() {
    }
}