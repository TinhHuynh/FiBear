package com.fibear.android.fibear.data.network

import com.fibear.android.fibear.data.model.ApiPostResponse
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.block.BearBlocksResult
import com.fibear.android.fibear.data.model.bear.detail.BearDetailResult
import com.fibear.android.fibear.data.model.bear.list.BearListResult
import retrofit2.Call

import com.fibear.android.fibear.data.model.login.LoginResult
import com.fibear.android.fibear.data.model.order.OrderRequestBody
import retrofit2.http.*


/**
 * Created by TINH HUYNH on 3/17/2018.
 */

interface FiBearService {
    @POST("v1/auth/login")
    fun fetchLoginResult(@Body user: User): Call<LoginResult>

    @GET("v1/bear/bears")
    fun fetchBearList(@Header("Authorization") token: String): Call<BearListResult>

    @GET("v1/bear/bears/{bear_id}")
    fun fetchBearDetail(@Header("Authorization") token: String,
                        @Path("bear_id") bearId: Int): Call<BearDetailResult>

    @GET("v1/bear/blocks/{bear_id}")
    fun fetchBearBlockByTime(@Header("Authorization") token: String,
                             @Path("bear_id") bearId: Int,
                             @Query("date") date: Long,
                             @Query("userId") user: Int): Call<BearBlocksResult>

    /**
     * format data example:</br>
     * {"blockId":8,"userId":8}
     */
    @POST("v1/order/block")
    fun orderBlock(@Header("Authorization") token: String,
                             @Body requestBody: OrderRequestBody): Call<ApiPostResponse>

}




