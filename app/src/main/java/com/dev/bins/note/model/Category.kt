package com.dev.bins.note.model

import org.litepal.crud.DataSupport

import java.util.ArrayList

/**
 * Created by bin on 11/24/15.
 */
class Category : DataSupport() {


    var id: Long = 0
    var categoty: String? = null

    var notes: List<Note> = ArrayList()

    companion object {

        val DEFAULT = 1
        val CLIPBOARD = 2
        val PRIVATE = 3
    }
}
