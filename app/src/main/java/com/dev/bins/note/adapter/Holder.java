package com.dev.bins.note.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.dev.bins.note.R;

/**
 * Created by bin on 11/26/15.
 */
public class Holder extends RecyclerView.ViewHolder {

    TextView title;
    TextView content;
    TextView time;
    MaterialRippleLayout cardView;

    public Holder(final View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.content);
        time = (TextView) itemView.findViewById(R.id.time);
        cardView = (MaterialRippleLayout) itemView.findViewById(R.id.cardView);
    }

}
