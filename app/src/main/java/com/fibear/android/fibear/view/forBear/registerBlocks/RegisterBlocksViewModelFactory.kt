package com.fibear.android.fibear.view.forBear.registerBlocks

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fibear.android.fibear.data.FiBearRepository

/**
 * Created by TINH HUYNH on 3/25/2018.
 */
class RegisterBlocksViewModelFactory(private val mFiBearRepository: FiBearRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            RegisterBlocksViewModel(mFiBearRepository) as T

}