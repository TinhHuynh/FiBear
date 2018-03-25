package com.fibear.android.fibear.data.model.order

import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.UserBlockDate
import com.google.gson.annotations.SerializedName

data class Order(
        @SerializedName("userBlockDate")
        val userBlockDate: UserBlockDate? = null,

        @SerializedName("id")
        val id: Int? = null,

        @SerializedName("user")
        val user: User? = null
)