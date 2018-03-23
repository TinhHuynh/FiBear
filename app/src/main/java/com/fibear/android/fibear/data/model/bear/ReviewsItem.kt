package com.fibear.android.fibear.data.model.bear

import com.fibear.android.fibear.data.model.bear.detail.UserReviewed
import com.google.gson.annotations.SerializedName

data class ReviewsItem(

	@SerializedName("rate")
	val rate: String? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("userReviewed")
	val userReviewed: UserReviewed? = null
)