package com.fibear.android.fibear

import com.fibear.android.fibear.data.model.User

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class SessionAttrs {
    companion object {
        lateinit var currentUser: User
        lateinit var token: String
    }
}