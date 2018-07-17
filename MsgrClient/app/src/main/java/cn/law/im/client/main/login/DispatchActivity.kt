package cn.law.im.client.main.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import cn.law.im.client.Constants
import cn.law.im.client.utils.PrefrencesUtils

class DispatchActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (PrefrencesUtils.getBoolean(Constants.Prefs.Login.FIRST)) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, SplashActivity::class.java))
        }
        finish()
    }
}