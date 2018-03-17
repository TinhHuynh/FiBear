package com.fibear.android.fibear.data.network

import com.fibear.android.fibear.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

import com.fibear.android.fibear.data.login.LoginResult
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by TINH HUYNH on 3/17/2018.
 */

interface FiBearService {
    @POST("v1/auth/login")
    fun fetchLoginResult(@Body user: User): Call<LoginResult>
}




