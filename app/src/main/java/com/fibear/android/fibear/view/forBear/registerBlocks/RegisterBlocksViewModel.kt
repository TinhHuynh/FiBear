package com.fibear.android.fibear.view.forBear.registerBlocks

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.model.ApiPostResponse
import com.fibear.android.fibear.data.model.bear.registerBlock.RegisterBlocksResult
import com.fibear.android.fibear.data.model.bear.registerBlock.RequestBody

/**
 * Created by TINH HUYNH on 3/25/2018.
 */

class RegisterBlocksViewModel(private val mFiBearRepository: FiBearRepository) : ViewModel() {
    private lateinit var mRegisterBlocks: LiveData<RegisterBlocksResult>
    private lateinit var mRegisterBlocksResponse: LiveData<ApiPostResponse>


    fun fetchRegisterBlocks(token: String, bearId: Int, date: Long): LiveData<RegisterBlocksResult> {
        mRegisterBlocks = mFiBearRepository.fetchRegisterBlocksByDate(token, bearId, date)
        return mRegisterBlocks
    }

    fun registerBlocks(token: String, requestBody: RequestBody): LiveData<ApiPostResponse> {
        mRegisterBlocksResponse = mFiBearRepository.registerBlocks(token, requestBody)
        return mRegisterBlocksResponse
    }

}
