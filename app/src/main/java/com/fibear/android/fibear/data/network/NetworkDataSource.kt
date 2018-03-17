package com.fibear.android.fibear.data.network

import com.fibear.android.fibear.data.FiBearRepository

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class NetworkDataSource private constructor(){
    companion object {
        private var INSTANCE: NetworkDataSource? = null

        @JvmStatic
        fun getInstance(): NetworkDataSource {
            if (INSTANCE == null) {
                synchronized(NetworkDataSource::javaClass) {
                    INSTANCE = NetworkDataSource()
                }
            }
            return INSTANCE!!
        }

    }
}