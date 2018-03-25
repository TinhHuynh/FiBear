package com.fibear.android.fibear.data.model

import com.google.gson.annotations.SerializedName

data class UserBlockDate(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("blockDate") var blockDate: String? = null,
        @SerializedName("price") var price: Int? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("block") val block: Block? = null,
        @SerializedName("status") var status: String? = null
) {
    fun title(): String = "${block?.title()}"
}