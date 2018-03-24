package com.fibear.android.fibear.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.fibear.android.fibear.R
import com.fibear.android.fibear.SessionAttrs
import com.fibear.android.fibear.data.model.User
import com.fibear.android.fibear.utils.InjectionUtils
import com.fibear.android.fibear.view.bearDetail.BearDetailActivity
import com.fibear.android.fibear.view.SpaceItemdDecorator
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.find


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnBearItemClickedListener {


    private lateinit var mViewModel: MainViewModel
    private var mBearListAdapter: BearListAdapter? = null

    companion object {
        private const val NUM_OF_ROW = 2
        fun getIntent(packageContext: Context) = Intent(packageContext, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSupportActionBar()
        initNavView()
        initViewModel()
        initSwipeRefreshLayout()
        initRecyclerView()
        fetchListBear()

    }

    private fun initSwipeRefreshLayout() {
        swiperefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            fetchListBear()
        })

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBearClicked(bear: User) {
        startActivity(BearDetailActivity.getIntent(this, bear))
    }

    private fun initSupportActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun initNavView() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        updateNavHeader()
    }

    private fun updateNavHeader() {
        //avatar
        val imageAvatar: CircleImageView = nav_view
                .getHeaderView(0)
                .find(R.id.image_user_avatar)

        Picasso.with(this)
                .load(SessionAttrs.currentUser.profile?.avatar)
                .placeholder(R.drawable.ic_default_user)
                .into(imageAvatar)

        //full name
        val textUsername: TextView = nav_view
                .getHeaderView(0)
                .find(R.id.txt_user_name)
        textUsername.text = SessionAttrs.currentUser.fullname()

        //email
        val textEmail: TextView = nav_view
                .getHeaderView(0)
                .find(R.id.txt_user_email)
        textEmail.text = SessionAttrs.currentUser.email
    }

    private fun initRecyclerView() {
        rv_bears.layoutManager = GridLayoutManager(this, NUM_OF_ROW)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.material_layout_keylines_screen_edge_margin) / 2
        rv_bears.addItemDecoration(SpaceItemdDecorator(spacingInPixels))
    }

    private fun initViewModel() {
        val factory = InjectionUtils.provideMainViewModelFactory()
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    private fun fetchListBear() {
        pb_bear_list.visibility = View.VISIBLE
        mViewModel.fetchBearList(SessionAttrs.token)
                .observe(this, Observer {
                    handleBearList(it?.users)
                    pb_bear_list.visibility = View.GONE
                })
    }

    private fun handleBearList(bears: List<User>?) {
        bears?.let {
            if (mBearListAdapter == null) {
                mBearListAdapter = BearListAdapter(this, bears, this)
                rv_bears.adapter = mBearListAdapter
            } else {
                mBearListAdapter?.bearList = bears
                mBearListAdapter?.notifyDataSetChanged()
            }
            if(swiperefresh.isRefreshing){
                swiperefresh.isRefreshing = false
            }
        }
    }


}
