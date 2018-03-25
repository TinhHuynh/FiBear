package com.fibear.android.fibear.view.forBear.registerBlocks

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fibear.android.fibear.R
import com.fibear.android.fibear.SessionAttrs
import com.fibear.android.fibear.data.model.UserBlockDate
import com.fibear.android.fibear.data.model.bear.registerBlock.RequestBody
import com.fibear.android.fibear.utils.DateUtils
import com.fibear.android.fibear.utils.InjectionUtils
import com.fibear.android.fibear.view.forBear.adapters.RegisterBlockAdapter
import kotlinx.android.synthetic.main.activity_register_blocks.*

class RegisterBlocksActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: RegisterBlocksViewModel

    companion object {
        fun getIntent(packageContext: Context): Intent = Intent(packageContext, RegisterBlocksActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_blocks)
        btn_register_block.setOnClickListener(this)
        initRecyclerView()
        initViewModel()
        fetchRegisterBlocks()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_register_block -> onRegisterBlocks()
        }
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
        val registeredBlocksCount = (rv_register_blocks.adapter as RegisterBlockAdapter)
                .getRegisteredBlocksCount()
        if (registeredBlocksCount == rv_register_blocks.adapter.itemCount) {
            btn_register_block.isEnabled = false
            btn_register_block.setBackgroundColor(ContextCompat.getColor(this, R.color.material_grey_400))
        }
    }

    private fun onRegisterBlocks() {
        showConfirmDialog()
    }

    private fun showConfirmDialog() {
        AlertDialog.Builder(this)
                .setTitle("Confirm dialog")
                .setMessage("Are you sure to register these blocks? You won't edit registered blocks in the future")
                .setPositiveButton("Yes, I'm sure", { dialog, _ ->
                    prepareRegisterBlockes()
                    dialog.dismiss()
                })
                .setNegativeButton("No, take me out", null)
                .show()
    }

    private fun prepareRegisterBlockes() {
        with(rv_register_blocks) {
            for (i in 0 until childCount) {
                val viewHolder = rv_register_blocks.findViewHolderForAdapterPosition(i)
                        as RegisterBlockAdapter.RegisterBlockViewHolder
                viewHolder.updateUserBlockDate()
            }
            prepareRequestBodyAndSendRequest((adapter as RegisterBlockAdapter).selectedUserBlockDate)
        }
    }

    private fun prepareRequestBodyAndSendRequest(selectedUserBlockDate: MutableList<UserBlockDate>) {
        val requestBody = RequestBody(SessionAttrs.currentUser.id, selectedUserBlockDate)
        mViewModel.registerBlocks(SessionAttrs.token, requestBody)
                .observe(this, Observer {
                    with(it) {
                        if (this!!.isSuccessful()) {
                            Snackbar.make(scrollView, "Registered blocks successfully",
                                    Snackbar.LENGTH_SHORT)
                                    .show()
                            fetchRegisterBlocks()
                        } else {
                            Snackbar.make(scrollView, "Failed to register blocks. Please try again",
                                    Snackbar.LENGTH_SHORT)
                                    .show()
                        }
                    }
                })
    }


}
