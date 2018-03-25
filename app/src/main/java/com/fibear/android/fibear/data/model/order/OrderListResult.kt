package com.fibear.android.fibear.data.model.order

import com.google.gson.annotations.SerializedName

data class OrderListResult(
	@SerializedName("orders")
	val orders: List<Order?>? = null
)