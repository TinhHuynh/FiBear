package com.fibear.android.fibear.data.model.bear.detail

import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.review.ReviewsItem
import com.google.gson.annotations.SerializedName

data class BearDetailResult(
        @SerializedName("user") val bear: User,
        @SerializedName("reviews") val reviews: List<ReviewsItem?>? = null
)