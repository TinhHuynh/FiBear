package com.fibear.android.fibear.data.model

import com.fibear.android.fibear.utils.DateUtils
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
data class User(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("email") val email: String? = null,
        @SerializedName("username") val username: String? = null,
        @SerializedName("password") val password: String? = null,
        @SerializedName("roleId") val roleId: Int? = null,
        @SerializedName("profile") val profile: Profile? = null
) : Serializable {

    fun rating(): String = "${profile?.starRate}/5"

    fun title(): String {
        return "${fullname()}, ${age()}"
    }

    fun age(): Int {
        val dateValues = profile?.birthdate?.split("-")
        val age = DateUtils.calculateAge(dateValues?.get(0)?.toInt()!!,
                dateValues[1].toInt(),
                dateValues[2].toInt())
        return age
    }

    fun fullname(): String = "${profile?.firstname} ${profile?.lastname}"

    constructor() : this(null)
}