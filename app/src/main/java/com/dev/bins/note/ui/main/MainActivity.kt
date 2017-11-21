package com.dev.bins.note.ui.main

import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.dev.bins.note.R
import com.dev.bins.note.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initView() {
        initToolbar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout!!.setDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun initData() {
        fragmentManager = supportFragmentManager
        initFragmnet()
    }

    //    private FloatingActionButton fab;
    private var fragmentManager: FragmentManager? = null


    private fun initFragmnet() {
    }


    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId



        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }
}
