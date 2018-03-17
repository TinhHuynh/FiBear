package com.fibear.android.fibear.view

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.fibear.android.fibear.Config
import com.fibear.android.fibear.Session
import com.fibear.android.fibear.view.login.LoginActivity
import com.orhanobut.hawk.Hawk
import org.jetbrains.anko.toast


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            if(!Hawk.contains(Config.PREF_TOKEN)){
                goToLoginScreen()
            }else{
                Session.token = Hawk.get(Config.PREF_TOKEN)
            }
        }, SPLASH_DISPLAY_LENGTH)
    }

    fun goToLoginScreen(){
        val intent = LoginActivity.getIntent(this)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SPLASH_DISPLAY_LENGTH: Long = 1000
    }
}
