package com.fibear.android.fibear.data

import com.fibear.android.fibear.data.network.NetworkDataSource

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class FiBearRepository private constructor(val networkDataSource: NetworkDataSource) {

    companion object {
        private var INSTANCE: FiBearRepository? = null

        @JvmStatic
        fun getInstance(networkDataSource: NetworkDataSource): FiBearRepository {
            if (INSTANCE == null) {
                synchronized(FiBearRepository::javaClass) {
                    INSTANCE = FiBearRepository(networkDataSource)
                }
            }
            return INSTANCE!!
        }

    }
}