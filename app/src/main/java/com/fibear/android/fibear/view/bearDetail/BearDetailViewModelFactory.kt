package com.fibear.android.fibear.view.bearDetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.fibear.android.fibear.data.FiBearRepository

/**
 * Created by TINH HUYNH on 3/23/2018.
 */
class BearDetailViewModelFactory(private val mFiBearRepository: FiBearRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = BearDetailViewModel(mFiBearRepository) as T

}