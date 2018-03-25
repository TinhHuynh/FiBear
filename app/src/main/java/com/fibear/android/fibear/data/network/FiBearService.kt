package com.fibear.android.fibear.data.network

import com.fibear.android.fibear.data.model.ApiPostResponse
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.registerBlock.RegisterBlocksResult
import com.fibear.android.fibear.data.model.bear.block.BearBlocksResult
import com.fibear.android.fibear.data.model.bear.detail.BearDetailResult
import com.fibear.android.fibear.data.model.bear.list.BearListResult
import retrofit2.Call

import com.fibear.android.fibear.data.model.login.LoginResult
import com.fibear.android.fibear.data.model.order.OrderListResult
import com.fibear.android.fibear.data.model.order.OrderRequestBody
import retrofit2.http.*


/**
 * Created by TINH HUYNH on 3/17/2018.
 */

interface FiBearService {

    // auth
    @POST("v1/auth/login")
    fun fetchLoginResult(@Body user: User): Call<LoginResult>

    //bear
    @GET("v1/bear/bears")
    fun fetchBearList(@Header("Authorization") token: String): Call<BearListResult>

    @GET("v1/bear/bears/{bear_id}")
    fun fetchBearDetail(@Header("Authorization") token: String,
                        @Path("bear_id") bearId: Int): Call<BearDetailResult>

    @GET("v1/bear/blocks/{bear_id}")
    fun fetchBearBlockByDate(@Header("Authorization") token: String,
                             @Path("bear_id") bearId: Int,
                             @Query("date") date: Long,
                             @Query("userId") user: Int): Call<BearBlocksResult>

    @GET("v1/bear/blocks/register/{bear_id}")
    fun fetchRegisterBlocksByDate(@Header("Authorization") token: String,
                                  @Path("bear_id") bearId: Int,
                                  @Query("date") date: Long): Call<RegisterBlocksResult>

    //order
    @POST("v1/order/block")
    fun orderBlock(@Header("Authorization") token: String,
                   @Body requestBody: OrderRequestBody): Call<ApiPostResponse>

    @GET("v1/order/orders/{bear_id}")
    fun fetchOrderListByDate(@Header("Authorization") token: String,
                             @Path("bear_id") bearId: Int,
                             @Query("date") date: Long): Call<OrderListResult>

}




