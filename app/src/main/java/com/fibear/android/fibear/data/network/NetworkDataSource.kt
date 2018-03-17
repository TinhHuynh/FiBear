package com.fibear.android.fibear.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.nfc.Tag
import android.util.Log
import com.fibear.android.fibear.data.User
import com.fibear.android.fibear.data.login.LoginResult
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject


/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class NetworkDataSource(val mFiBearApiClient: FiBearService) {

    companion object {
        private val TAG = "NetworkDataSource"

        private var INSTANCE: NetworkDataSource? = null

        @JvmStatic
        fun getInstance(fiBearApiClient: FiBearService): NetworkDataSource {
            if (INSTANCE == null) {
                synchronized(NetworkDataSource::javaClass) {
                    INSTANCE = NetworkDataSource(fiBearApiClient)
                }
            }
            return INSTANCE!!
        }

    }

    fun fetchLoginResult(loginUser: User): LiveData<LoginResult> {
        val pendingResult = MutableLiveData<LoginResult>()
        mFiBearApiClient.fetchLoginResult(loginUser)
                .enqueue(object : Callback<LoginResult> {
                    override fun onFailure(call: Call<LoginResult>, t: Throwable?) {
                        Log.e(TAG, t?.message)
                    }

                    override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                        with(response) {
                            call.request().url()
                            if (isSuccessful) {
                                pendingResult.postValue(response.body())
                            } else {
                                val jError = JSONObject(errorBody()?.string())
                                val error = jError.getString("error")
                                val code = jError.getInt("code")
                                val loginResult = LoginResult(null, null, code, error)
                                pendingResult.postValue(loginResult)
                            }
                        }
                    }
                })
        return pendingResult
    }
}