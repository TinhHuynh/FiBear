package com.fibear.android.fibear.view.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fibear.android.fibear.data.FiBearRepository

class LoginViewModelFactory(private val mRepository: FiBearRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(mRepository) as T
    }
}