package com.dev.bins.note.model

import org.litepal.crud.DataSupport

import java.util.Date

/**
 * Created by bin on 11/24/15.
 */
class Note : DataSupport() {

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
