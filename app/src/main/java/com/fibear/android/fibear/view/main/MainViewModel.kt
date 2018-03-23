package com.fibear.android.fibear.view.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.model.bear.list.BearListResult

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class MainViewModel(var mRepository: FiBearRepository) : ViewModel() {

    private lateinit var mBearList: LiveData<BearListResult>

    fun getLoginResult(): LiveData<BearListResult> {
        return mBearList
    }

    fun fetchBearList(token: String): LiveData<BearListResult> {
        mBearList = mRepository.fetchBearList(token)
        return mBearList
    }

}