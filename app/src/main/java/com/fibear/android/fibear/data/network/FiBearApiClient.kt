package com.fibear.android.fibear.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class FiBearApiClient {

    companion object {
        private var SERVICE: FiBearService? = null

        fun getClient(): FiBearService {
                if (SERVICE == null) {
                    val retrofit = Retrofit.Builder()
                            .baseUrl(Config.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    SERVICE = retrofit.create(FiBearService::class.java)
                }
            return SERVICE!!
        }
    }

}