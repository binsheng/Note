package com.dev.bins.note.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message

import com.dev.bins.note.R

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : Activity() {


    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler.sendEmptyMessageDelayed(1, 3000)

    }


}
