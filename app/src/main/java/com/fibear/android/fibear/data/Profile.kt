package com.fibear.android.fibear.data

import com.google.gson.annotations.SerializedName

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
data class Profile(
        @SerializedName("id") val id: Int,
        @SerializedName("avatar") val avatar: String,
        @SerializedName("firstname") val firstname: String,
        @SerializedName("lastname") val lastname: String,
        @SerializedName("birthdate") val birthdate: String,
        @SerializedName("sex") val sex: Int
)