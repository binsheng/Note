package com.dev.bins.note.model

import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

/**
 * Created by bin on 11/24/15.
 */
class Note : BaseModel() {

    var id: Long = 0
    var title: String? = null
    var content: String? = null
    var date: Date? = null
    var isShow: Boolean = false

    var category: Category? = null

    override fun toString(): String {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", isShow=" + isShow +
                ", category=" + category +
                '}'
    }
}
