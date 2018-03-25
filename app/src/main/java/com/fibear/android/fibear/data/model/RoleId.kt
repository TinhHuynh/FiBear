package com.fibear.android.fibear.data.model

/**
 * Created by TINH HUYNH on 3/25/2018.
 */
enum class RoleId(private val id: Int) {
    USER(1), BEAR(2);

    fun getId(): Int = id
}