package com.fibear.android.fibear.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.fibear.android.fibear.R
import com.fibear.android.fibear.SessionAttrs
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun getIntent(packageContext: Context) = Intent(packageContext, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSupportActionBar()
        initNavView()
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
                .findViewById(R.id.image_user_avatar)

        Picasso.with(this)
                .load(SessionAttrs.currentUser.profile?.avatar)
                .placeholder(R.drawable.ic_logo)
                .into(imageAvatar)

        //full name
        val textUsername: TextView = nav_view
                .getHeaderView(0)
                .findViewById(R.id.txt_user_name)
        textUsername.text = SessionAttrs.currentUser.fullname()

        //email
        val textEmail: TextView = nav_view
                .getHeaderView(0)
                .findViewById(R.id.txt_user_email)
        textEmail.text = SessionAttrs.currentUser.email
    }


}
