package com.fibear.android.fibear.view.bearDetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.model.ApiPostResponse
import com.fibear.android.fibear.data.model.bear.block.BearBlocksResult
import com.fibear.android.fibear.data.model.bear.detail.BearDetailResult
import com.fibear.android.fibear.data.model.order.OrderRequestBody

/**
 * Created by TINH HUYNH on 3/23/2018.
 */
class BearDetailViewModel(private val mFiBearRepository: FiBearRepository) : ViewModel() {
    private lateinit var mBearDetail: LiveData<BearDetailResult>
    private lateinit var mBearBlock: LiveData<BearBlocksResult>
    private lateinit var mOrderBlockResponse: LiveData<ApiPostResponse>

    fun fetchBearDetail(token: String, bearId: Int): LiveData<BearDetailResult> {
        mBearDetail = mFiBearRepository.fetchBearDetail(token, bearId)
        return mBearDetail
    }

    fun fetchBearBlocksByDate(token: String, bearId: Int, date: Long, userId: Int): LiveData<BearBlocksResult> {
        mBearBlock = mFiBearRepository.fetchBearBlocksByDate(token, bearId, date, userId)
        return mBearBlock
    }

    fun orderBlock(token: String, requestBody: OrderRequestBody): LiveData<ApiPostResponse> {
        mOrderBlockResponse = mFiBearRepository.orderToken(token, requestBody)
        return mOrderBlockResponse
    }
}