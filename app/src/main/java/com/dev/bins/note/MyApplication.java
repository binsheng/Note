package com.dev.bins.note;

import com.dev.bins.note.utils.InitData;
import com.dev.bins.note.utils.SharePreference;

import org.litepal.LitePalApplication;

/**
 * Created by bin on 11/24/15.
 */
public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        boolean isFirst = SharePreference.readFromSharePerference(getApplicationContext(), SharePreference.IS_FIRST);
        if (isFirst){
            InitData.initCategory();
            SharePreference.saveToSharePerference(getApplicationContext(),SharePreference.IS_FIRST,false);
        }
    }
}
