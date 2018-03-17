package com.fibear.android.fibear.data

import com.google.gson.annotations.SerializedName

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
data class User(
        @SerializedName("email") val email: String,
        @SerializedName("username") val username: String,
        @SerializedName("profile") val profile: List<Profile>
)