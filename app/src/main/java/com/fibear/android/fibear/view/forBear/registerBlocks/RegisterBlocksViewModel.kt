package com.fibear.android.fibear.view.forBear.registerBlocks

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.fibear.android.fibear.data.FiBearRepository
import com.fibear.android.fibear.data.model.bear.registerBlock.RegisterBlocksResult

/**
 * Created by TINH HUYNH on 3/25/2018.
 */

class RegisterBlocksViewModel(private val mFiBearRepository: FiBearRepository) : ViewModel() {
    private lateinit var mRegiterBlocks: LiveData<RegisterBlocksResult>

    fun fetchRegisterBlocks(token: String, bearId: Int, date: Long): LiveData<RegisterBlocksResult> {
        mRegiterBlocks = mFiBearRepository.fetchRegisterBlocksByDate(token, bearId, date)
        return mRegiterBlocks
    }

}
