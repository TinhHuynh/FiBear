package com.fibear.android.fibear

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            goToLoginScreen()
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
