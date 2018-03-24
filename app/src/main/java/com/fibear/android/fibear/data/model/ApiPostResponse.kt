package com.fibear.android.fibear.data.model

data class ApiPostResponse(
        private val message: String? = null,
        private val error: String? = null,
        private val status: String? = null
){
    fun isSuccessful():Boolean = message != null
}
