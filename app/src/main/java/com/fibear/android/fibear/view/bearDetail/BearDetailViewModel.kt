package com.fibear.android.fibear.view.bearDetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.model.bear.detail.BearDetailResult

/**
 * Created by TINH HUYNH on 3/23/2018.
 */
class BearDetailViewModel(private val mFiBearRepository: FiBearRepository) : ViewModel() {
    private lateinit var mBearDetail: LiveData<BearDetailResult>

    fun fetchBearDetail(token: String, bearId: Int): LiveData<BearDetailResult> {
        mBearDetail = mFiBearRepository.fetchBearDetail(token, bearId)
        return mBearDetail
    }
}