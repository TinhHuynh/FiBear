package com.fibear.android.fibear.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
data class User(
        @SerializedName("email") val email: String? = null,
        @SerializedName("username") val username: String? = null,
        @SerializedName("password") val password: String? = null,
        @SerializedName("roleId") val roleId: Int? = null,
        @SerializedName("profile") val profile: Profile? = null
) : Serializable {

    fun fullname(): String = "${profile?.firstname} ${profile?.lastname}"

    constructor() : this(null)
}