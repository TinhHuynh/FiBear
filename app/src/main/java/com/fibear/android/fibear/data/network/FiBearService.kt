package com.fibear.android.fibear.data.network

import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.list.BearListResult
import retrofit2.Call

import com.fibear.android.fibear.data.model.login.LoginResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


/**
 * Created by TINH HUYNH on 3/17/2018.
 */

interface FiBearService {
    @POST("v1/auth/login")
    fun fetchLoginResult(@Body user: User): Call<LoginResult>

    @GET("v1/bear/bears")
    fun fetchBearList(@Header("Authorization") token: String): Call<BearListResult>
}




