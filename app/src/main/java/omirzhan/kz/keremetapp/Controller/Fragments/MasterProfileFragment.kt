package omirzhan.kz.keremetapp.Controller.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_master_profile.view.*
import omirzhan.kz.keremetapp.Adapters.PostAdapter
import omirzhan.kz.keremetapp.Model.PostData
import omirzhan.kz.keremetapp.Model.UserData

import omirzhan.kz.keremetapp.R

class MasterProfileFragment : Fragment() {


    val TAG = MasterProfileFragment::class.java.simpleName
    var mView: View? = null

    private lateinit var mAdapter: PostAdapter
    var postArray: List<PostData> = listOf(PostData("name", 123,compound = "dadasda",delivery = false, type = "tort"),
            PostData("name", 123,compound = "dadasda",delivery = false, type = "tort"),
            PostData("name", 123,compound = "dadasda",delivery = false, type = "tort"))
    var userData = UserData(userEmail = "name", userName = "email", userPhone = "777", userImageUrl = "photo")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar!!.title = "Мой профиль"
        mView = inflater.inflate(R.layout.fragment_master_profile, container, false)


        mAdapter = PostAdapter(postArray, userData)
        val mLayoutManager = LinearLayoutManager(activity)
        mView!!.recyclerView.layoutManager = mLayoutManager
        mView!!.recyclerView.adapter = mAdapter

        return mView
    }


}
