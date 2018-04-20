package omirzhan.kz.keremetapp.Controller.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_master_posts.view.*
import nextstep.kz.tazalyk.adapter.RecyclerItemClickListener
import omirzhan.kz.keremetapp.Adapters.PostAdapter
import omirzhan.kz.keremetapp.Controller.InfoActivity
import omirzhan.kz.keremetapp.Model.PostData

import omirzhan.kz.keremetapp.R

class MasterPostsFragment : Fragment() {

    private var mView: View? = null
    private lateinit var mAdapter: PostAdapter
    private val TAG = MasterPostsFragment::class.java.simpleName
    var postArray: List<PostData> = listOf(PostData(title = "title", price = "5000", compound = "comp", delivery = false, userID = "", type = "", imageUrl = ""),
            PostData(title = "title", price = "5000", compound = "comp", delivery = false, userID = "", type = "", imageUrl = ""),
            PostData(title = "title", price = "5000", compound = "comp", delivery = false, userID = "", type = "", imageUrl = ""))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar!!.title = "KeremetApp"

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_master_posts, container, false)

            mAdapter = PostAdapter(postArray)
            val mLayoutManager = LinearLayoutManager(activity)
            mView!!.recyclerView.layoutManager = mLayoutManager
            mView!!.recyclerView.adapter = mAdapter
            mView!!.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(activity as AppCompatActivity,
                    mView!!.recyclerView, object: RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val intent = Intent(activity, InfoActivity::class.java)
                    startActivity(intent)
                }

                override fun onItemLongClick(view: View?, position: Int) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }))
        }
        Log.d(TAG, "onCreateView")
        setHasOptionsMenu(true)
        return mView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.posts, menu)
    }


}
