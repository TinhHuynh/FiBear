package com.fibear.android.fibear.utils

import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.network.FiBearApiClient
import com.fibear.android.fibear.data.network.FiBearService
import com.fibear.android.fibear.data.network.NetworkDataSource
import com.fibear.android.fibear.view.login.LoginViewModelFactory

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class InjectionUtils {
    companion object {
        fun provideFiBearApiClient(): FiBearService = FiBearApiClient.getClient()

        fun provideFibearReposiory(): FiBearRepository
                = FiBearRepository.getInstance(provideNetworkDataSource())

        fun provideNetworkDataSource(): NetworkDataSource = NetworkDataSource.getInstance(provideFiBearApiClient())

        fun provideLoginViewModelFactory(): LoginViewModelFactory = LoginViewModelFactory(provideFibearReposiory())
    }
}