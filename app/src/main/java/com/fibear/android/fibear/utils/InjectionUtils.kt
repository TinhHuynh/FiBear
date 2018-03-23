package com.fibear.android.fibear.utils

import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.network.FiBearApiClient
import com.fibear.android.fibear.data.network.FiBearService
import com.fibear.android.fibear.data.network.NetworkDataSource
import com.fibear.android.fibear.view.bearDetail.BearDetailViewModelFactory
import com.fibear.android.fibear.view.login.LoginViewModelFactory
import com.fibear.android.fibear.view.main.MainViewModelFactory

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class InjectionUtils {
    companion object {
        fun provideFiBearApiClient(): FiBearService = FiBearApiClient.getClient()

        fun provideFibearReposiory(): FiBearRepository = FiBearRepository.getInstance(provideNetworkDataSource())

        fun provideNetworkDataSource(): NetworkDataSource = NetworkDataSource.getInstance(provideFiBearApiClient())

        // view model factory
        fun provideLoginViewModelFactory(): LoginViewModelFactory = LoginViewModelFactory(provideFibearReposiory())

        fun provideMainViewModelFactory(): MainViewModelFactory = MainViewModelFactory(provideFibearReposiory())

        fun provideBearDetailViewModelFactory(): BearDetailViewModelFactory = BearDetailViewModelFactory(provideFibearReposiory())

    }
}