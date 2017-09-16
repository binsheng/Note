package com.dev.bins.note.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast

import com.dev.bins.note.R
import com.dev.bins.note.model.Category
import com.dev.bins.note.model.Note

import org.litepal.crud.DataSupport

import java.util.Date

import butterknife.ButterKnife
import butterknife.InjectView

class AddNoteActivity : AppCompatActivity() {

    @InjectView(R.id.toolbar)
    internal var toolbar: Toolbar? = null
    @InjectView(R.id.et_title)
    internal var etTitle: EditText? = null
    @InjectView(R.id.et_content)
    internal var etContent: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        ButterKnife.inject(this)
        val id = intent.getLongExtra("id", 0)
        if (id != 0) {
            initData(id)
        }
        initToolBar()

    }

    private fun initData(id: Long) {
        val note = DataSupport.find<Note>(Note::class.java, id)
        etTitle!!.setText(note.title)
        etContent!!.setText(note.content)
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setNavigationOnClickListener { finish() }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.abandon -> finish()
            R.id.save -> {
                val title = etTitle!!.text.toString()
                val content = etContent!!.text.toString()
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    Snackbar.make(item.actionView, "标题或者内容不能为空", Snackbar.LENGTH_SHORT).show()
                } else {
                    val note = Note()
                    note.title = title
                    note.content = content
                    note.date = Date()
                    note.isShow = true
                    note.save()

                    val category = DataSupport.find<Category>(Category::class.java, Category.DEFAULT.toLong())
                    category.notes.add(note)
                    category.save()
                    Toast.makeText(this@AddNoteActivity, "保存成功", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, DetailActivity::class.java)
                    intent.putExtra("id", note.id)
                    startActivity(intent)
                    finish()
                }
            }
        }

        return true
    }
}
