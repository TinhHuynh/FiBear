package com.fibear.android.fibear.data.model

import com.google.gson.annotations.SerializedName

data class UserBlockDate(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("blockDate") val blockDate: String? = null,
        @SerializedName("price") val price: Int? = null,
        @SerializedName("description") val description: String? = null,
        @SerializedName("block") val block: Block? = null,
        @SerializedName("status") val status: String? = null
) {
    fun title(): String = "${block?.title()}"
}