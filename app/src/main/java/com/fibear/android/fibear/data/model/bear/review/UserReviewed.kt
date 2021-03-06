package com.fibear.android.fibear.data.model.bear.review

import com.google.gson.annotations.SerializedName

data class UserReviewed(
        @SerializedName("email")
        val email: String? = null,

        @SerializedName("username")
        val username: String? = null
)