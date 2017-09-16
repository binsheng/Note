package com.dev.bins.note

import com.dev.bins.note.utils.InitData
import com.dev.bins.note.utils.SharePreference

import org.litepal.LitePalApplication

/**
 * Created by bin on 11/24/15.
 */
class MyApplication : LitePalApplication() {
    override fun onCreate() {
        super.onCreate()
        val isFirst = SharePreference.readFromSharePerference(applicationContext, SharePreference.IS_FIRST)
        if (isFirst) {
            InitData.initCategory()
            SharePreference.saveToSharePerference(applicationContext, SharePreference.IS_FIRST, false)
        }
    }
}
