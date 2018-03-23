package com.fibear.android.fibear.data.model.bear

import com.google.gson.annotations.SerializedName

data class Block(
        @SerializedName("hourEnd") val hourEnd: String? = null,
        @SerializedName("hourStart") val hourStart: String? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("description") val description: String? = null
)