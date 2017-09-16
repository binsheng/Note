package com.dev.bins.note.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import com.dev.bins.note.R
import com.dev.bins.note.model.Note

import org.litepal.crud.DataSupport

import butterknife.ButterKnife
import butterknife.InjectView


class DetailActivity : AppCompatActivity(), View.OnClickListener {


    @InjectView(R.id.title)
    internal var title: TextView? = null
    @InjectView(R.id.content)
    internal var content: TextView? = null
    @InjectView(R.id.detail_toolbar)
    internal var detailToolbar: Toolbar? = null
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        id = intent.getLongExtra("id", 1)
        ButterKnife.inject(this)
        initToolbar()
        initData()
    }

    private fun initData() {
        val note = DataSupport.find<Note>(Note::class.java, id)
        val titles = note.title
        if (titles == "无标题") {
            title!!.text = note.id.toString()
        } else {
            title!!.text = titles
        }
        content!!.text = note.content

    }

    private fun initToolbar() {
        setSupportActionBar(detailToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        detailToolbar!!.setNavigationOnClickListener(this)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.edit -> {
                val intent = Intent(applicationContext, AddNoteActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
            R.id.del -> {
                val values = ContentValues()
                values.put("isshow", false)
                DataSupport.update(Note::class.java, values, id)
                finish()
            }
        }

        return true
    }

    override fun onClick(v: View) {
        finish()
    }
}
