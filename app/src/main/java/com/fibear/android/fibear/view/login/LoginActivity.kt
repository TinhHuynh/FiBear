package com.fibear.android.fibear.view.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.fibear.android.fibear.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener { attemptLogin() }
    }


    private fun attemptLogin() {
        // Reset errors.
        et_username.error = null
        et_password.error = null

        // Store values at the time of the login attempt.
        val emailStr = et_username.text.toString()
        val passwordStr = et_password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(passwordStr)) {
            et_password.error = getString(R.string.error_field_required)
            focusView = et_password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            et_username.error = getString(R.string.error_field_required)
            focusView = et_username
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else {
            showProgress(true)
        }
    }

    private fun showProgress(isShown: Boolean) {
        pb_login.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    companion object {
        fun getIntent(packageContext: Context) = Intent(packageContext, LoginActivity::class.java)
    }
}
