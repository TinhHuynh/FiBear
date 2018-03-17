package com.fibear.android.fibear

import com.fibear.android.fibear.data.User

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class Session {
    companion object {
        lateinit var currentUser: User
        lateinit var token: String
    }
}