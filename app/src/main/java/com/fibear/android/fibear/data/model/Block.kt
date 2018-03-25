package com.fibear.android.fibear.data.model

import com.google.gson.annotations.SerializedName

data class Block(
		@SerializedName("id") val Int: String? = null,
		@SerializedName("hourEnd") val hourEnd: String? = null,
		@SerializedName("hourStart") val hourStart: String? = null,
		@SerializedName("name") val name: String? = null,
		@SerializedName("description") val description: String? = null,
		@SerializedName("isCreated") val isCreated: Boolean? = false

){
    fun title(): String = "${name?.replace(" Block", "")} (${description?.trim()})"
}