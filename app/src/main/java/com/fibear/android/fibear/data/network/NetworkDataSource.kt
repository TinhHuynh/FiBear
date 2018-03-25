package com.fibear.android.fibear.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.fibear.android.fibear.data.model.ApiPostResponse
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.registerBlock.RegisterBlocksResult
import com.fibear.android.fibear.data.model.bear.block.BearBlocksResult
import com.fibear.android.fibear.data.model.bear.detail.BearDetailResult
import com.fibear.android.fibear.data.model.bear.list.BearListResult
import com.fibear.android.fibear.data.model.bear.registerBlock.RequestBody
import com.fibear.android.fibear.data.model.login.LoginResult
import com.fibear.android.fibear.data.model.order.OrderRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class NetworkDataSource(private val mFiBearApiClient: FiBearService) {

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

    fun fetchBearList(token: String): LiveData<BearListResult> {
        val pendingResult = MutableLiveData<BearListResult>()
        mFiBearApiClient.fetchBearList(token)
                .enqueue(object : Callback<BearListResult> {
                    override fun onFailure(call: Call<BearListResult>, t: Throwable?) {
                        Log.e(TAG, t?.message)
                    }

                    override fun onResponse(call: Call<BearListResult>, response: Response<BearListResult>) {
                        with(response) {
                            call.request().url()
                            if (isSuccessful) {
                                pendingResult.postValue(response.body())
                            }
                        }
                    }
                })
        return pendingResult
    }

    fun fetchBearDetail(token: String, bearId: Int): LiveData<BearDetailResult> {
        val pendingResult = MutableLiveData<BearDetailResult>()
        mFiBearApiClient.fetchBearDetail(token, bearId)
                .enqueue(object : Callback<BearDetailResult> {
                    override fun onFailure(call: Call<BearDetailResult>, t: Throwable?) {
                        Log.e(TAG, t?.message)
                    }

                    override fun onResponse(call: Call<BearDetailResult>, response: Response<BearDetailResult>) {
                        with(response) {
                            call.request().url()
                            if (isSuccessful) {
                                pendingResult.postValue(response.body())
                            }
                        }
                    }
                })
        return pendingResult
    }

    fun fetchBearBlocksByDate(token: String, bearId: Int, date: Long, userId: Int): LiveData<BearBlocksResult> {
        val pendingResult = MutableLiveData<BearBlocksResult>()
        mFiBearApiClient.fetchBearBlockByDate(token, bearId, date, userId)
                .enqueue(object : Callback<BearBlocksResult> {
                    override fun onFailure(call: Call<BearBlocksResult>, t: Throwable?) {
                        Log.e(TAG, t?.message)
                    }

                    override fun onResponse(call: Call<BearBlocksResult>, response: Response<BearBlocksResult>) {
                        with(response) {
                            if (isSuccessful) {
                                pendingResult.postValue(response.body())
                            }
                        }
                    }
                })
        return pendingResult
    }

    fun fetchRegisterBlocksByDate(token: String, bearId: Int, date: Long): LiveData<RegisterBlocksResult> {
        val pendingResult = MutableLiveData<RegisterBlocksResult>()
        mFiBearApiClient.fetchRegisterBlocksByDate(token, bearId, date)
                .enqueue(object : Callback<RegisterBlocksResult> {
                    override fun onFailure(call: Call<RegisterBlocksResult>, t: Throwable?) {
                        Log.e(TAG, t?.message)
                    }

                    override fun onResponse(call: Call<RegisterBlocksResult>, response: Response<RegisterBlocksResult>) {
                        with(response) {
                            if (isSuccessful)
                                pendingResult.postValue(response.body())
                        }
                    }
                })
        return pendingResult
    }

    fun orderBlock(token: String, requestBody: OrderRequestBody): LiveData<ApiPostResponse> {
        val pendingResult = MutableLiveData<ApiPostResponse>()
        mFiBearApiClient.orderBlock(token, requestBody)
                .enqueue(object : Callback<ApiPostResponse> {
                    override fun onFailure(call: Call<ApiPostResponse>, t: Throwable?) {
                        Log.e(TAG, t?.message)
                    }

                    override fun onResponse(call: Call<ApiPostResponse>, response: Response<ApiPostResponse>) {
                        with(response) {
                            if(response.isSuccessful)
                                pendingResult.postValue(response.body())
                            else{
                                val respondBody = ApiPostResponse()
                                pendingResult.postValue(respondBody)

                            }
                        }
                    }
                })
        return pendingResult
    }

    fun registerBlocks(token: String, requestBody: RequestBody): LiveData<ApiPostResponse> {
        val pendingResult = MutableLiveData<ApiPostResponse>()
        mFiBearApiClient.registerBlocks(token, requestBody)
                .enqueue(object : Callback<ApiPostResponse> {
                    override fun onFailure(call: Call<ApiPostResponse>, t: Throwable?) {
                        Log.e(TAG, t?.message)
                    }

                    override fun onResponse(call: Call<ApiPostResponse>, response: Response<ApiPostResponse>) {
                        with(response) {
                            if(response.isSuccessful)
                                pendingResult.postValue(response.body())
                            else{
                                val respondBody = ApiPostResponse()
                                pendingResult.postValue(respondBody)
                                print(response.errorBody())
                                val jError = JSONObject(errorBody()?.string())
                                val error = jError.getString("error")
                                print(error)
                            }
                        }
                    }
                })
        return pendingResult
    }

}

