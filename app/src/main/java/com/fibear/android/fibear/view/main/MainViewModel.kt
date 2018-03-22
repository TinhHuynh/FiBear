package com.fibear.android.fibear.view.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.model.User

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class MainViewModel(var mRepository: FiBearRepository) : ViewModel() {

    private lateinit var mBearList: LiveData<List<User>>

    fun getLoginResult(): LiveData<List<User>> {
        return mBearList
    }

    fun fetchBearList(): LiveData<List<User>> {
        mBearList = mRepository.fetchBearList()
        return mBearList
    }

}