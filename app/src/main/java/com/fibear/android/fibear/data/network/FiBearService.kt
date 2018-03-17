package com.fibear.android.fibear.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

import com.fibear.android.fibear.data.login.LoginResult


/**
 * Created by TINH HUYNH on 3/17/2018.
 */

class FiBearService {
    interface GitHubService {
        @GET("v1/auth/login")
        fun listRepos(@Path("user") user: String): Call<LoginResult>

    }
}




