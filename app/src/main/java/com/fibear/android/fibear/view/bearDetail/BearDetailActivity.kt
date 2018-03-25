package com.fibear.android.fibear.view.bearDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.fibear.android.fibear.R
import com.fibear.android.fibear.SessionAttrs
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.block.BlockStatus
import com.fibear.android.fibear.data.model.UserBlockDate
import com.fibear.android.fibear.data.model.bear.review.Review
import com.fibear.android.fibear.data.model.order.OrderRequestBody
import com.fibear.android.fibear.utils.DateUtils
import com.fibear.android.fibear.utils.InjectionUtils
import com.fibear.android.fibear.view.bearDetail.adapters.BearBlockAdapter
import com.fibear.android.fibear.view.bearDetail.adapters.BearReviewAdapter
import com.fibear.android.fibear.view.bearDetail.adapters.OnBlockClickedListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_bear_detail.*
import kotlinx.android.synthetic.main.content_bear_detail.*
import org.jetbrains.anko.find


class BearDetailActivity : AppCompatActivity(), View.OnClickListener, OnBlockClickedListener {

    private lateinit var mBear: User
    private lateinit var mViewModel: BearDetailViewModel
    private lateinit var mBlockDialog: AlertDialog

    companion object {
        const val ARG_BEAR = "ARG_BEAR"

        fun getIntent(packageContext: Context, bear: User): Intent {
            val intent = Intent(packageContext, BearDetailActivity::class.java)
            intent.putExtra(ARG_BEAR, bear)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bear_detail)
        mBear = intent.getSerializableExtra(ARG_BEAR) as User

        fab_order_bear.setOnClickListener(this)

        initViewModel()
        initSupportActionBar()
        initReviewRecyclerView()
        bindBearBasicInfo()
        fetchBearDetail()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_order_bear ->{
                fetchBearBlocksToday()
            }

        }
    }

    override fun onBlockClicked(block: UserBlockDate) {
        showConfirmDialog(block.id!!)
        mBlockDialog.dismiss()
    }

    private fun showConfirmDialog(blockId: Int) {
        AlertDialog.Builder(this)
                .setTitle("Confirm dialog")
                .setMessage("Are you sure to hire this Bear ? Please take a deep breathe before deciding :)")
                .setPositiveButton("Yes, I'm sure", { dialog, _ ->
                    orderBlock(blockId)
                    dialog.dismiss()
                })
                .setNegativeButton("No, take me out", null)
                .show()
    }

    private fun orderBlock(blockId: Int) {
        mViewModel.orderBlock(SessionAttrs.token, OrderRequestBody(blockId, SessionAttrs.currentUser.id!!))
                .observe(this, Observer {
                    with(it) {
                        if (it!!.isSuccessful()) {
                            this@BearDetailActivity.showOrderResultDialog(it.isSuccessful())
                        }
                    }
                })
    }

    private fun showOrderResultDialog(successful: Boolean) {
        val dialog = AlertDialog.Builder(this)
        if (successful) {
            dialog.setMessage("Order is placed successfully. All what you need to do is waiting a " +
                    "response from the Bear. Please be patient")
        } else {
            dialog.setMessage("You have already ordered this Bear")
        }
        dialog.setPositiveButton(android.R.string.ok, null).show()
    }

    private fun initViewModel() {
        val factory: BearDetailViewModelFactory = InjectionUtils.provideBearDetailViewModelFactory()
        mViewModel = ViewModelProviders.of(this, factory).get(BearDetailViewModel::class.java)
    }

    private fun initSupportActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initReviewRecyclerView() {
        with(rv_reviews) {
            layoutManager = LinearLayoutManager(this@BearDetailActivity)

            layoutManager as LinearLayoutManager

            val dividerItemDecoration = DividerItemDecoration(context,
                    (layoutManager as LinearLayoutManager).orientation)
            addItemDecoration(dividerItemDecoration)
        }

    }

    private fun bindBearBasicInfo() {
        with(mBear) {
            Picasso.with(this@BearDetailActivity)
                    .load(profile?.avatar)
                    .into(image_main_avatar)
            Picasso.with(this@BearDetailActivity)
                    .load(profile?.avatar)
                    .into(image_background)
            txt_rating.text = rating()
            supportActionBar?.title = title()
            txt_description.text = profile?.description
        }
    }

    private fun fetchBearDetail() {
        pb_reviews.visibility = View.VISIBLE
        mViewModel.fetchBearDetail(SessionAttrs.token, mBear.id!!)
                .observe(this, Observer { result ->
                    with(result) {
                        mBear = this?.bear!!
                        reviews?.let {
                            card_review.visibility = View.VISIBLE
                            handleReviewList(it)
                        }
                        if (reviews == null) {
                            Snackbar.make(layout_bear_detail, "This bear hasn't had any review yet"
                                    , Snackbar.LENGTH_SHORT).show()
                        }
                        pb_reviews.visibility = View.GONE
                    }
                })
    }

    private fun handleReviewList(reviews: List<Review?>?) {
        rv_reviews.adapter = BearReviewAdapter(this, reviews as List<Review>)
    }

    private fun fetchBearBlocksToday() {
        val today = DateUtils.todayInMillisecs()
        var list: List<UserBlockDate>? = null
        mViewModel.fetchBearBlocksByDate(SessionAttrs.token, mBear.id!!, today,
                SessionAttrs.currentUser.id!!)
                .observe(this, Observer { result ->
                    result?.userBlockDates?.let { it ->
                        list = it
                                .filter {
                                    it?.status == BlockStatus.FREE.name
                                }
                                .sortedWith(compareBy({ it?.block?.hourStart }))
                                .toList() as List<UserBlockDate>
                    }
                    showBlocksDialog(list)
                })
    }

    private fun showBlocksDialog(userBlockDates: List<UserBlockDate>?) {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_blocks, null)
        if (userBlockDates != null) {
            setUpBlockRecyclerView(view, userBlockDates)
        }
        mBlockDialog = AlertDialog.Builder(this)
                .setView(view)
                .show()
    }

    private fun setUpBlockRecyclerView(view: View, userBlockDates: List<UserBlockDate>) {
        val rvBlocks: RecyclerView = view.find(R.id.rv_blocks)
        view.find<TextView>(R.id.txt_no_block).visibility = View.GONE
        with(rvBlocks) {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(context)
            val dividerItemDecoration = DividerItemDecoration(context,
                    (layoutManager as LinearLayoutManager).orientation)
            addItemDecoration(dividerItemDecoration)
            adapter = BearBlockAdapter(context, userBlockDates, this@BearDetailActivity)
        }
    }


}
