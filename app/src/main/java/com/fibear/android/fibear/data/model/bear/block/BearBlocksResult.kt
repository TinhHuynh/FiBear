package com.fibear.android.fibear.data.model.bear.block

import com.google.gson.annotations.SerializedName

data class BearBlocksResult(
        @SerializedName("userBlockDates") val userBlockDates: List<UserBlockDatesItem?>? = null
)