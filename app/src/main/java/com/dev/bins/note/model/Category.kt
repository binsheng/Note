package com.dev.bins.note.model

import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

/**
 * Created by bin on 11/24/15.
 */
class Category: BaseModel() {


    var id: Long = 0
    var categoty: String? = null

    var notes: List<Note> = ArrayList()

    companion object {

        val DEFAULT = 1
        val CLIPBOARD = 2
        val PRIVATE = 3
    }
}
