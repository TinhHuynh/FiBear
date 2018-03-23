package com.fibear.android.fibear.data.model.bear.list

import com.fibear.android.fibear.data.model.User
import com.google.gson.annotations.SerializedName

/**
 * Created by TINH HUYNH on 3/22/2018.
 */
data class BearListResult(
        @SerializedName("users") val users: List<User>? = null
)