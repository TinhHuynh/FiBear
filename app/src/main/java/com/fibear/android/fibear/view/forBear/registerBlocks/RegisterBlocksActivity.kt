package com.fibear.android.fibear.view.forBear.registerBlocks

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.fibear.android.fibear.R
import com.fibear.android.fibear.SessionAttrs
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.UserBlockDate
import com.fibear.android.fibear.utils.DateUtils
import com.fibear.android.fibear.utils.InjectionUtils
import com.fibear.android.fibear.view.forBear.adapters.RegisterBlockAdapter
import kotlinx.android.synthetic.main.activity_register_blocks.*

class RegisterBlocksActivity : AppCompatActivity() {

    private lateinit var mViewModel: RegisterBlocksViewModel

    companion object {
        fun getIntent(packageContext: Context): Intent = Intent(packageContext, RegisterBlocksActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_blocks)
        initRecyclerView()
        initViewModel()
        fetchRegisterBlocks()
    }


    private fun initRecyclerView() {
        rv_register_blocks.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this,
                (rv_register_blocks.layoutManager as LinearLayoutManager).orientation)
        rv_register_blocks.addItemDecoration(itemDecoration)
    }

    private fun initViewModel() {
        val factory: RegisterBlocksViewModelFactory = InjectionUtils.provideRegisterBlocksViewModelFactory()
        mViewModel = ViewModelProviders.of(this, factory)[RegisterBlocksViewModel::class.java]
    }

    private fun fetchRegisterBlocks() {
        val today = DateUtils.todayInMillisecs()
        mViewModel.fetchRegisterBlocks(SessionAttrs.token, SessionAttrs.currentUser.id!!, today)
                .observe(this, Observer {
                    with(it) {
                        handleBlocks((this?.userBlockDates as List<UserBlockDate>?)!!)
                    }
                })
    }

    private fun handleBlocks(userBlockDates: List<UserBlockDate>) {
        rv_register_blocks.adapter = RegisterBlockAdapter(this, userBlockDates)
    }

}
