package com.dev.bins.note.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

/**
 * Created by bin on 21/11/2017.
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()

    abstract fun initData()

    fun initToolbar(toolbar: Toolbar) {
        initToolbar(toolbar, false)
    }

    fun initToolbar(toolbar: Toolbar, back: Boolean) {
        setSupportActionBar(toolbar)
        if (back) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }


}