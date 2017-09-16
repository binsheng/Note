package com.dev.bins.note.ui

import android.app.ActivityManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MotionEvent
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast

import com.dev.bins.note.R
import com.dev.bins.note.service.CopyService
import com.dev.bins.note.views.OptionSwitch

import butterknife.ButterKnife
import butterknife.InjectView

class SettingsActivity : AppCompatActivity() {

    @InjectView(R.id.switch_service)
    internal var switchService: OptionSwitch? = null
    @InjectView(R.id.toolbar)
    internal var toolbar: Toolbar? = null
    @InjectView(R.id.notification)
    internal var notification: OptionSwitch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        ButterKnife.inject(this)
        initToolbar()
        switchService!!.setmSwitch(isServiceWork)
        switchService!!.setOnClickListener {
            val isSwitch = switchService!!.isSwitch
            switchService!!.setmSwitch(!isSwitch)
            val intent = Intent(applicationContext, CopyService::class.java)
            if (!isSwitch) {
                startService(intent)
            } else {
                stopService(intent)
            }
        }

        notification!!.setOnClickListener {
            val mBuilder = NotificationCompat.Builder(applicationContext)
                    .setSmallIcon(R.mipmap.ic_add_white)
                    .setContentTitle("My notification")
                    .setContentText("Hello World!")
            val resultIntent = Intent(applicationContext, CopyService::class.java)
            resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val resultPendingIntent = PendingIntent.getService(
                    applicationContext,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
            //                mBuilder.setContentIntent(resultPendingIntent);

            val views = RemoteViews(packageName, R.layout.notification_layout)
            views.setOnClickPendingIntent(R.id.nf_switch, resultPendingIntent)


            mBuilder.setContent(views)


            val mNotificationId = 1
            val mNotifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotifyMgr.notify(mNotificationId, mBuilder.build())

            Toast.makeText(this@SettingsActivity, "嘿嘿还没有实现的功能!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setNavigationOnClickListener { finish() }
    }


    private val isServiceWork: Boolean
        get() {
            var isWork = false
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val runningServices = activityManager.getRunningServices(40)
            if (runningServices.size <= 0) return false
            for (runningService in runningServices) {

                val name = runningService.service.className.toString()

                if (name == "com.dev.bins.note.service.CopyService") {
                    isWork = true
                    break
                }
            }

            return isWork
        }


}
