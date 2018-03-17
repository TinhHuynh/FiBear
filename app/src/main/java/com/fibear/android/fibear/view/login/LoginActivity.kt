package com.fibear.android.fibear.view.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.fibear.android.fibear.Config
import com.fibear.android.fibear.R
import com.fibear.android.fibear.Session
import com.fibear.android.fibear.data.User
import com.fibear.android.fibear.data.login.LoginResult
import com.fibear.android.fibear.utils.InjectionUtils
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity() {

    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViewModel()

        btn_login.setOnClickListener { attemptLogin() }
    }

    private fun initViewModel() {
        val factory = InjectionUtils.provideLoginViewModelFactory()
        mViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
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
            val loginUser = User(
                    null,
                    et_username.text.toString(),
                    et_password.text.toString(),
                    null)

            mViewModel.fetchLoginResult(loginUser).observe(this,
                    Observer<LoginResult> { result: LoginResult? ->
                        with(result) {
                            if (this?.user != null && this.token != null) {
                                Session.currentUser = user
                                Session.token = token
                                saveTokenToPreferences(token)
                                toast("Welcome ${user.profile?.get(0)?.firstname}")
                            } else {
                                toast(if (this?.error != null) error else "Invalid username or password")
                            }
                            showProgress(false)
                        }
                    })
        }
    }

    private fun saveTokenToPreferences(token: String) {
        Hawk.put(Config.PREF_TOKEN, token)
    }


    private fun showProgress(isShown: Boolean) {
        pb_login.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    companion object {
        fun getIntent(packageContext: Context) = Intent(packageContext, LoginActivity::class.java)
    }
}
