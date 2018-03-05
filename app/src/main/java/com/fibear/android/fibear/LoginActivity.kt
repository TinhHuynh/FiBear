package com.fibear.android.fibear

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener { attemptLogin() }
    }


    private fun attemptLogin() {
        // Reset errors.
        et_email.error = null
        et_password.error = null

        // Store values at the time of the login attempt.
        val emailStr = et_email.text.toString()
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
            et_email.error = getString(R.string.error_field_required)
            focusView = et_email
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            et_email.error = getString(R.string.error_invalid_email)
            focusView = et_email
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


    private fun isEmailValid(email: String): Boolean
            = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    companion object {
        fun getIntent(packageContext: Context) = Intent(packageContext, LoginActivity::class.java)
    }
}
