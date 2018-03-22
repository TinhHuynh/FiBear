package com.fibear.android.fibear.view.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.login.LoginResult

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class LoginViewModel(var mRepository: FiBearRepository) : ViewModel() {

    private lateinit var mLoginResult: LiveData<LoginResult>

    fun getLoginResult(): LiveData<LoginResult> {
        return mLoginResult
    }

    fun fetchLoginResult(loginUser: User): LiveData<LoginResult> {
        mLoginResult = mRepository.fetchLoginResult(loginUser)
        return mLoginResult
    }


}