package com.dev.bins.note.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by bin on 11/25/15.
 */
object SharePreference {
    val CONFIG_NAME = "config"

    val IS_FIRST = "first"

    fun saveToSharePerference(context: Context, key: String, value: Boolean) {
        val sharedPreferences = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun readFromSharePerference(context: Context, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, true)

    }

}
