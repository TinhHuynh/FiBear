package com.fibear.android.fibear.utils

import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.network.NetworkDataSource

/**
 * Created by TINH HUYNH on 3/17/2018.
 */
class InjectionUtils {
    companion object {
        fun provideFibearResposiory(networkDataSource: NetworkDataSource): FiBearRepository
                = FiBearRepository.getInstance(networkDataSource)
        fun provideNetworkDataSource(): NetworkDataSource = NetworkDataSource.getInstance()
    }
}