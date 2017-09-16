package com.dev.bins.note.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.Switch
import android.widget.TextView

import com.dev.bins.note.R

/**
 * Created by bin on 11/24/15.
 */
class OptionSwitch @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private var title: TextView? = null
    private var desc: TextView? = null
    private var mSwitch: Switch? = null
    private val desc_on: String?
    private val desc_off: String?

    init {
        View.inflate(context, R.layout.option_switch, this)
        initView()
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.OptionSwitch, defStyleAttr, 0)
        val title = typedArray.getString(R.styleable.OptionSwitch_show_title)
        desc_off = typedArray.getString(R.styleable.OptionSwitch_desc_off)
        desc_on = typedArray.getString(R.styleable.OptionSwitch_desc_on)
        setTitle(title)
        setDesc(desc_off)
    }

    private fun initView() {
        title = findViewById(R.id.title) as TextView
        desc = findViewById(R.id.desc) as TextView
        mSwitch = findViewById(R.id.sw) as Switch
    }


    fun setTitle(text: String?) {
        title!!.text = text
    }

    fun setDesc(text: String?) {
        desc!!.text = text
    }

    val isSwitch: Boolean
        get() = mSwitch!!.isChecked

    fun setmSwitch(option: Boolean) {
        if (option) {
            desc!!.text = desc_on
        } else {
            desc!!.text = desc_off
        }
        mSwitch!!.isChecked = option
    }


}
