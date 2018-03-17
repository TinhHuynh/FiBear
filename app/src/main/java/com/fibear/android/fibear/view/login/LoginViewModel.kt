package com.fibear.android.fibear.view.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.User
import com.fibear.android.fibear.data.login.LoginResult

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class LoginViewModel(var mRepository: FiBearRepository) : ViewModel() {

    private lateinit var mWeather: LiveData<LoginResult>

    fun getLoginResult(): LiveData<LoginResult> {
        return mWeather
    }

    fun fetchLoginResult(loginUser: User): LiveData<LoginResult> = mRepository.fetchLoginResult(loginUser)


}