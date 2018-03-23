package com.fibear.android.fibear.view.bear_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fibear.android.fibear.R
import com.fibear.android.fibear.data.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_bear_detail.*
import kotlinx.android.synthetic.main.content_bear_detail.*


class BearDetailActivity : AppCompatActivity() {

    lateinit var bear: User

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

        bear = intent.getSerializableExtra(ARG_BEAR) as User
        initSupportActionBar()
        bindUserBearToUI()
    }

    private fun initSupportActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Picasso.with(this)
                .load(bear.profile?.avatar)
                .into(image_avatar)

        supportActionBar?.title = bear.title()
    }

    private fun bindUserBearToUI() {
        txt_description.text = bear.profile?.description
    }

}
