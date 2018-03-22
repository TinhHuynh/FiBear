package com.fibear.android.fibear.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
data class Profile(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("avatar") val avatar: String? = null,
        @SerializedName("firstname") val firstname: String? = null,
        @SerializedName("lastname") val lastname: String? = null,
        @SerializedName("birthdate") val birthdate: String? = null,
        @SerializedName("sex") val sex: Int? = null,
        @SerializedName("description") val description: String? = null
) : Serializable {

    constructor() : this(null)
}