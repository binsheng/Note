package com.dev.bins.note.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.bins.note.R;
import com.dev.bins.note.model.Note;
import com.dev.bins.note.ui.DetailActivity;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by bin on 11/26/15.
 */
public class TrashRecycleAdapter extends RecyclerView.Adapter<Holder> {

    private Context context;
    private  SimpleDateFormat sdf;
    private List<Note> notes;

    public TrashRecycleAdapter(Context context) {
        this.context = context;
        sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        notes = DataSupport.where("isshow = ?","0").order("date desc").find(Note.class);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Note note = notes.get(position);
        String title = note.getTitle();
        if (title.equals("无标题")) {
            holder.title.setText(String.valueOf(note.getId()));
        } else {
            holder.title.setText(title);
        }
        holder.content.setText(note.getContent());
        String date = sdf.format(note.getDate());
        holder.time.setText(date);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", note.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public void add(List<Note> data) {
        notes.clear();
        notes.addAll(data);
        notifyDataSetChanged();
    }
}
