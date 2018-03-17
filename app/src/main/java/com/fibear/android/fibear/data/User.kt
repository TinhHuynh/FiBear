package com.fibear.android.fibear.data

import com.google.gson.annotations.SerializedName

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
data class User(
        @SerializedName("email") val email: String? = null,
        @SerializedName("username") val username: String? = null,
        @SerializedName("password") val password: String? = null,
        @SerializedName("profile") val profile: List<Profile>? = null
)