package com.dev.bins.note.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.balysv.materialripple.MaterialRippleLayout
import com.dev.bins.note.R

/**
 * Created by bin on 11/26/15.
 */
class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal var title: TextView
    internal var content: TextView
    internal var time: TextView
    internal var cardView: MaterialRippleLayout

    init {
        title = itemView.findViewById(R.id.title) as TextView
        content = itemView.findViewById(R.id.content) as TextView
        time = itemView.findViewById(R.id.time) as TextView
        cardView = itemView.findViewById(R.id.cardView) as MaterialRippleLayout
    }

}
