package com.fibear.android.fibear.data.model.bear.registerBlock

import com.fibear.android.fibear.data.model.Block
import com.fibear.android.fibear.data.model.UserBlockDate
import com.google.gson.annotations.SerializedName

class RegisterBlocksResult(
        @SerializedName("userBlockDates") val userBlockDates: List<UserBlockDate?>? = null

)