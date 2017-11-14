package com.dev.bins.note.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Message
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
import com.dev.bins.note.adapter.TrashRecycleAdapter
import com.dev.bins.note.model.Category
import com.dev.bins.note.model.Note


/**
 * A simple [Fragment] subclass.
 */
class TrashFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    internal var recycleView: RecyclerView? = null
    internal var swipe: SwipeRefreshLayout? = null

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                if (swipe != null && swipe!!.isRefreshing) {
                    swipe!!.isRefreshing = false
                }
                adapter!!.add(msg.obj as List<Note>)
            }
        }
    }
    private var adapter: TrashRecycleAdapter? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_trash, container, false)
        swipe!!.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe!!.setOnRefreshListener(this)
        recycleView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleView!!.itemAnimator = DefaultItemAnimator()
        adapter = TrashRecycleAdapter(context)
        recycleView!!.adapter = adapter
        if (adapter!!.itemCount == 0) {
            Snackbar.make(view!!, "木有内容哦!!!", Snackbar.LENGTH_SHORT).show()
        }

    }


    override fun onRefresh() {

    }
}// Required empty public constructor
