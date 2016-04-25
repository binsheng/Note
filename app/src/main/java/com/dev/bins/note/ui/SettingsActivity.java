package com.dev.bins.note.ui;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.dev.bins.note.R;
import com.dev.bins.note.service.CopyService;
import com.dev.bins.note.views.OptionSwitch;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SettingsActivity extends AppCompatActivity {

    @InjectView(R.id.switch_service)
    OptionSwitch switchService;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.notification)
    OptionSwitch notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.inject(this);
        initToolbar();
        switchService.setmSwitch(isServiceWork());
        switchService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSwitch = switchService.isSwitch();
                switchService.setmSwitch(!isSwitch);
                Intent intent = new Intent(getApplicationContext(), CopyService.class);
                if (!isSwitch) {
                    startService(intent);
                } else {
                    stopService(intent);
                }
            }

        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.mipmap.ic_add_white)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");
                Intent resultIntent = new Intent(getApplicationContext(), CopyService.class);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent resultPendingIntent =
                        PendingIntent.getService(
                                getApplicationContext(),
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
//                mBuilder.setContentIntent(resultPendingIntent);

                RemoteViews views = new RemoteViews(getPackageName(), R.layout.notification_layout);
                views.setOnClickPendingIntent(R.id.nf_switch,resultPendingIntent);


                mBuilder.setContent(views);


                int mNotificationId = 1;
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());

                Toast.makeText(SettingsActivity.this, "嘿嘿还没有实现的功能!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private boolean isServiceWork() {
        boolean isWork = false;
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(40);
        if (runningServices.size() <= 0) return false;
        for (ActivityManager.RunningServiceInfo runningService : runningServices) {

            String name = runningService.service.getClassName().toString();

            if (name.equals("com.dev.bins.note.service.CopyService")) {
                isWork = true;
                break;
            }
        }

        return isWork;
    }

    
}
