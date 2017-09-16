package com.dev.bins.note.service

import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.IBinder
import android.text.TextUtils
import android.widget.Toast

import com.dev.bins.note.model.Category
import com.dev.bins.note.model.Note

import org.litepal.crud.DataSupport

import java.util.Date


class CopyService : Service(), ClipboardManager.OnPrimaryClipChangedListener {

    private var clipboardManager: ClipboardManager? = null
    private var mPreText = ""
    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager!!.addPrimaryClipChangedListener(this)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onPrimaryClipChanged() {
        if (clipboardManager!!.hasPrimaryClip()) {
            val clipData = clipboardManager!!.primaryClip
            val data = clipData.getItemAt(0).coerceToText(applicationContext).toString()
            if (TextUtils.isEmpty(data))
                return
            if (mPreText == data) {
                return
            } else {
                mPreText = data
            }
            val note = Note()
            note.title = "无标题"
            note.content = data
            note.date = Date()
            note.isShow = true
            note.save()

            val category = DataSupport.find<Category>(Category::class.java, Category.CLIPBOARD.toLong())
            category.notes.add(note)
            category.save()
        }
    }

    override fun onDestroy() {
        clipboardManager!!.removePrimaryClipChangedListener(this)
        super.onDestroy()
    }
}
