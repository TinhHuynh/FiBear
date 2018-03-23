package com.fibear.android.fibear.view.bearDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.fibear.android.fibear.R
import com.fibear.android.fibear.SessionAttrs
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.data.model.bear.ReviewsItem
import com.fibear.android.fibear.utils.InjectionUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_bear_detail.*
import kotlinx.android.synthetic.main.content_bear_detail.*
import kotlinx.android.synthetic.main.content_main.*
import android.support.v7.widget.DividerItemDecoration




class BearDetailActivity : AppCompatActivity() {

    private lateinit var mBear: User
    private lateinit var mViewModel: BearDetailViewModel

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

        initViewModel()
        initSupportActionBar()
        initRecyclerView()
        bindBearBasicInfo()
        fetchBearDetail()
    }

    private fun initViewModel() {
        val factory: BearDetailViewModelFactory = InjectionUtils.provideBearDetailViewModelFactory()
        mViewModel = ViewModelProviders.of(this, factory).get(BearDetailViewModel::class.java)
    }

    private fun initSupportActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initRecyclerView() {
        with(rv_reviews){
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
        mViewModel.fetchBearDetail(SessionAttrs.token, mBear.id!!)
                .observe(this, Observer { result ->
                    with(result) {
                        mBear = this?.bear!!
                        reviews?.let {
                            handleReviewList(reviews)
                        }
                    }
                })
    }

    private fun handleReviewList(reviews: List<ReviewsItem?>?) {
        rv_reviews.adapter = BearReviewAdapter(this, reviews as List<ReviewsItem>)
    }

}
