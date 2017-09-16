package com.dev.bins.note.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

import com.dev.bins.note.R
import com.dev.bins.note.fragment.ClipBoardFragment
import com.dev.bins.note.fragment.MainFragment
import com.dev.bins.note.fragment.TrashFragment
import com.dev.bins.note.ui.SettingsActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var toolbar: Toolbar? = null
    //    private FloatingActionButton fab;
    private var drawer: DrawerLayout? = null
    private var fragmentManager: FragmentManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        fragmentManager = supportFragmentManager
        initFragmnet()
    }

    private fun initFragmnet() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, MainFragment())
        fragmentTransaction.commit()
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //        fab = (FloatingActionButton) findViewById(R.id.fab);
        //        fab.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                        .setAction("Action", null).show();
        //            }
        //        });

        drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        // Inflate the menu; this adds items to the action bar if it is present.
    //        getMenuInflater().inflate(R.menu.main, menu);
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        // Handle action bar item clicks here. The action bar will
    //        // automatically handle clicks on the Home/Up button, so long
    //        // as you specify a parent activity in AndroidManifest.xml.
    //        int id = item.getItemId();
    //
    //        //noinspection SimplifiableIfStatement
    //        if (id == R.id.action_settings) {
    //            return true;
    //        }
    //
    //        return super.onOptionsItemSelected(item);
    //    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragmentManager!!.beginTransaction().replace(R.id.fragment, MainFragment()).commit()
            toolbar!!.title = "默认"
        } else if (id == R.id.nav_gallery) {
            fragmentManager!!.beginTransaction().replace(R.id.fragment, TrashFragment()).commit()
            toolbar!!.title = "回收站"

        } else if (id == R.id.nav_slideshow) {
            fragmentManager!!.beginTransaction().replace(R.id.fragment, ClipBoardFragment()).commit()
            toolbar!!.title = "剪切板"
        } else if (id == R.id.nav_manage) {
            val intent = Intent(applicationContext, SettingsActivity::class.java)
            startActivity(intent)

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }
}
