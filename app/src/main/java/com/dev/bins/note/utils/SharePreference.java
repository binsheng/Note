package com.dev.bins.note.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bin on 11/25/15.
 */
public class SharePreference {
    public static final String CONFIG_NAME ="config";

    public static final String IS_FIRST = "first";

    public static void saveToSharePerference(Context context,String key,boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static boolean readFromSharePerference(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, true);

    }

}
