package com.fibear.android.fibear.data.model.bear.review

import com.google.gson.annotations.SerializedName

data class Review(

	@SerializedName("rate")
	val rate: Double? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("userReviewed")
	val userReviewed: UserReviewed? = null
)