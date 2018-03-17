package com.fibear.android.fibear

import android.app.Application
import com.orhanobut.hawk.Hawk

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}