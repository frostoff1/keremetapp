package omirzhan.kz.keremetapp.Controller.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_master_posts.view.*
import omirzhan.kz.keremetapp.Adapters.PostAdapter
import omirzhan.kz.keremetapp.Model.PostData

import omirzhan.kz.keremetapp.R

class MasterPostsFragment : Fragment() {

    private var mView: View? = null
    private lateinit var mAdapter: PostAdapter
    private val TAG = MasterPostsFragment::class.java.simpleName
    var postArray: List<PostData> = listOf(PostData("name", 123,compound = "dadasda",delivery = false, type = "tort"),
            PostData("name", 123,compound = "dadasda",delivery = false, type = "tort"),
            PostData("name", 123,compound = "dadasda",delivery = false, type = "tort"))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar!!.title = "KeremetApp"

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_master_posts, container, false)

            mAdapter = PostAdapter(postArray)
            val mLayoutManager = LinearLayoutManager(activity)
            mView!!.recyclerView.layoutManager = mLayoutManager
            mView!!.recyclerView.adapter = mAdapter
        }
        Log.d(TAG, "onCreateView")
        return mView
    }


}
