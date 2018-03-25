package com.fibear.android.fibear.data

import android.arch.lifecycle.LiveData
import com.fibear.android.fibear.data.model.ApiPostResponse
import com.fibear.android.fibear.data.model.login.LoginResult
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.registerBlock.RegisterBlocksResult
import com.fibear.android.fibear.data.model.bear.block.BearBlocksResult
import com.fibear.android.fibear.data.model.bear.detail.BearDetailResult
import com.fibear.android.fibear.data.model.bear.list.BearListResult
import com.fibear.android.fibear.data.model.order.OrderRequestBody
import com.fibear.android.fibear.data.network.NetworkDataSource

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class FiBearRepository private constructor(private val mNetworkDataSource: NetworkDataSource) {

    companion object {
        private var INSTANCE: FiBearRepository? = null

        @JvmStatic
        fun getInstance(networkDataSource: NetworkDataSource): FiBearRepository {
            if (INSTANCE == null) {
                synchronized(FiBearRepository::javaClass) {
                    INSTANCE = FiBearRepository(networkDataSource)
                }
            }
            return INSTANCE!!
        }

    }

    fun fetchLoginResult(loginUser: User): LiveData<LoginResult> = mNetworkDataSource.fetchLoginResult(loginUser)
    fun fetchBearList(token: String): LiveData<BearListResult> = mNetworkDataSource.fetchBearList(token)
    fun fetchBearDetail(token: String, bearId: Int): LiveData<BearDetailResult> = mNetworkDataSource.fetchBearDetail(token, bearId)
    fun fetchBearBlocksByDate(token: String, bearId: Int, date: Long, userId: Int): LiveData<BearBlocksResult>
            = mNetworkDataSource.fetchBearBlocksByDate(token, bearId, date, userId)
    fun fetchRegisterBlocksByDate(token: String, bearId: Int, date: Long): LiveData<RegisterBlocksResult>
            = mNetworkDataSource.fetchRegisterBlocksByDate(token, bearId, date)
    fun orderToken(token: String, requestBody: OrderRequestBody): LiveData<ApiPostResponse>
            = mNetworkDataSource.orderBlock(token, requestBody)

}