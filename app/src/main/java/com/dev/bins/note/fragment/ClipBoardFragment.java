package com.dev.bins.note.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.bins.note.R;
import com.dev.bins.note.adapter.ClipBoardRecycleViewAdapter;
import com.dev.bins.note.model.Note;
import com.dev.bins.note.ui.AddNoteActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClipBoardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @InjectView(R.id.clipboardRecycleView)
    RecyclerView clipboardRecycleView;
    @InjectView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    private ClipBoardRecycleViewAdapter adapter;

    public ClipBoardFragment() {
        // Required empty public constructor
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (swipe != null && swipe.isRefreshing()) {
                    swipe.setRefreshing(false);
                }
                adapter.add((List<Note>) msg.obj);
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clip_board, container, false);
        ButterKnife.inject(this, view);
        swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        onRefresh();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe.setOnRefreshListener(this);
        clipboardRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ClipBoardRecycleViewAdapter(getContext());
        clipboardRecycleView.setAdapter(adapter);
        clipboardRecycleView.setItemAnimator(new DefaultItemAnimator());
        if (adapter.getItemCount() == 0) {
            Snackbar.make(view, "木有内容哦", Snackbar.LENGTH_SHORT).show();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = DataSupport.where("isshow=? and category_id=?", "1", "2").order("date desc").find(Note.class);
        handler.sendMessageDelayed(msg, 3000);

    }
}
