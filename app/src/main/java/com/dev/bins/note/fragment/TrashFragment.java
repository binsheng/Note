package com.dev.bins.note.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.dev.bins.note.adapter.TrashRecycleAdapter;
import com.dev.bins.note.model.Category;
import com.dev.bins.note.model.Note;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrashFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (swipe!= null && swipe.isRefreshing()) {
                    swipe.setRefreshing(false);
                }
                adapter.add((List<Note>) msg.obj);
            }
        }
    };
    private TrashRecycleAdapter adapter;

    public TrashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trash, container, false);
        ButterKnife.inject(this, view);
        swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe.setOnRefreshListener(this);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycleView.setItemAnimator(new DefaultItemAnimator());
        adapter = new TrashRecycleAdapter(getContext());
        recycleView.setAdapter(adapter);
        if (adapter.getItemCount() == 0) {
            Snackbar.make(view, "木有内容哦!!!", Snackbar.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {

        List<Note> notes = DataSupport.where("isshow =? and category_id=?", "0", String.valueOf(Category.DEFAULT)).order("date desc").find(Note.class);
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = notes;
        handler.sendMessageDelayed(msg, 3000);
    }
}
