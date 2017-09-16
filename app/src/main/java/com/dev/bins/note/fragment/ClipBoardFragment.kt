package com.dev.bins.note.fragment


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dev.bins.note.R
import com.dev.bins.note.adapter.ClipBoardRecycleViewAdapter
import com.dev.bins.note.model.Note
import com.dev.bins.note.ui.AddNoteActivity

import org.litepal.crud.DataSupport

import butterknife.ButterKnife
import butterknife.InjectView

/**
 * A simple [Fragment] subclass.
 */
class ClipBoardFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    @InjectView(R.id.clipboardRecycleView)
    internal var clipboardRecycleView: RecyclerView? = null
    @InjectView(R.id.swipe)
    internal var swipe: SwipeRefreshLayout? = null
    @InjectView(R.id.fab)
    internal var fab: FloatingActionButton? = null
    private var adapter: ClipBoardRecycleViewAdapter? = null

    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                if (swipe != null && swipe!!.isRefreshing) {
                    swipe!!.isRefreshing = false
                }
                adapter!!.add(msg.obj as List<Note>)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_clip_board, container, false)
        ButterKnife.inject(this, view)
        swipe!!.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        return view
    }

    override fun onResume() {
        super.onResume()
        //        onRefresh();
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe!!.setOnRefreshListener(this)
        clipboardRecycleView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ClipBoardRecycleViewAdapter(context)
        clipboardRecycleView!!.adapter = adapter
        clipboardRecycleView!!.itemAnimator = DefaultItemAnimator()
        if (adapter!!.itemCount == 0) {
            Snackbar.make(view!!, "木有内容哦", Snackbar.LENGTH_SHORT).show()
        }
        fab!!.setOnClickListener {
            val intent = Intent(context, AddNoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.reset(this)
    }

    override fun onRefresh() {
        val msg = Message.obtain()
        msg.what = 1
        msg.obj = DataSupport.where("isshow=? and category_id=?", "1", "2").order("date desc").find<Note>(Note::class.java)
        handler.sendMessageDelayed(msg, 3000)

    }
}// Required empty public constructor
