package com.dev.bins.note.utils;

import android.database.sqlite.SQLiteDatabase;

import com.dev.bins.note.model.Category;

import org.litepal.tablemanager.Connector;

/**
 * Created by bin on 11/25/15.
 */
public class InitData {

    public static void initCategory(){
        SQLiteDatabase db = Connector.getDatabase();
        Category category = new Category();
        category.setCategoty("默认");
        category.save();

        Category category1 = new Category();
        category1.setCategoty("剪切板");
        category1.save();

        Category category2 = new Category();
        category2.setCategoty("私人");
        category2.save();
    }
}
