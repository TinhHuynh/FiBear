package com.fibear.android.fibear.view

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.fibear.android.fibear.Config
import com.fibear.android.fibear.SessionAttrs
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.view.login.LoginActivity
import com.fibear.android.fibear.view.main.MainActivity
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            if (!Hawk.contains(Config.PREF_TOKEN)) {
                goToLoginScreen()
            } else {
                SessionAttrs.token = Hawk.get(Config.PREF_TOKEN)
                SessionAttrs.currentUser = Gson().fromJson(Hawk.get(Config.PREF_USER) as String, User::class.java)
                goToMainScreen()
            }
        }, SPLASH_DISPLAY_LENGTH)
    }

    fun goToLoginScreen() {
        val intent = LoginActivity.getIntent(this)
        startActivity(intent)
        finish()
    }

    private fun goToMainScreen() {
        val intent = MainActivity.getIntent(this)
        startActivity(intent)
        finish()
    }


    companion object {
        private const val SPLASH_DISPLAY_LENGTH: Long = 1000
    }
}
