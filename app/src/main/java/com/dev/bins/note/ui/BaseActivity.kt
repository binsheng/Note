package com.dev.bins.note.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by bin on 21/11/2017.
 */
abstract class BaseActivity:AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
    }

    abstract fun initView();

    abstract fun initData();



    abstract fun getLayoutId(): Int;


}