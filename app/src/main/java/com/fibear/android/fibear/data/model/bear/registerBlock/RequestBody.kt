package com.fibear.android.fibear.data.model.bear.registerBlock

import com.fibear.android.fibear.data.model.UserBlockDate
import com.google.gson.annotations.SerializedName

data class RequestBody(
        @SerializedName("bearId")
        var bearId: Int? = null,

        @SerializedName("userBlockDate")
        var userBlockDate: List<UserBlockDate>? = null
)