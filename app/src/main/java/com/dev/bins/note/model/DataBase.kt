package com.dev.bins.note.model

import com.dev.bins.note.model.DataBase.Companion.DB_NAME
import com.dev.bins.note.model.DataBase.Companion.DB_VERSION
import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by bin on 21/11/2017.
 */
@Database(name = DB_NAME, version = DB_VERSION)
class DataBase{
    companion object {
        const val DB_NAME = "note_db"
        const val DB_VERSION = 1
    }
}