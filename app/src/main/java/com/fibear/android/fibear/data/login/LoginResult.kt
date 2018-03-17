package com.fibear.android.fibear.data.login

import com.fibear.android.fibear.data.User
import com.google.gson.annotations.SerializedName

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
data class LoginResult(
        @SerializedName("token") val token: String,
        @SerializedName("user") val user: User
)
