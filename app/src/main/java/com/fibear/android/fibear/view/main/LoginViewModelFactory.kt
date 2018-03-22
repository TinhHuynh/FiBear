package com.fibear.android.fibear.view.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.view.login.LoginViewModel

class MainViewModelFactory(private val mRepository: FiBearRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(mRepository) as T
    }
}