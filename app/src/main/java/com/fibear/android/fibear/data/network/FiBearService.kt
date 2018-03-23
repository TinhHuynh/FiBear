package com.fibear.android.fibear.data.network

import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.detail.BearDetailResult
import com.fibear.android.fibear.data.model.bear.list.BearListResult
import retrofit2.Call

import com.fibear.android.fibear.data.model.login.LoginResult
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
}




