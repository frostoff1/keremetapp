package omirzhan.kz.keremetapp.Controller.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_master_profile.view.*
import omirzhan.kz.keremetapp.Adapters.PostAdapter
import omirzhan.kz.keremetapp.Controller.ClientActivity
import omirzhan.kz.keremetapp.Controller.LoginActivity
import omirzhan.kz.keremetapp.Model.PostData
import omirzhan.kz.keremetapp.Model.UserData

import omirzhan.kz.keremetapp.R

class MasterProfileFragment : Fragment() {


    val TAG = MasterProfileFragment::class.java.simpleName
    var mView: View? = null

    private lateinit var mAdapter: PostAdapter
    var postArray: List<PostData> = listOf(PostData(title = "title", price = "5000", compound = "comp", delivery = false, userID = "", type = "", imageUrl = ""),
            PostData(title = "title", price = "5000", compound = "comp", delivery = false, userID = "", type = "", imageUrl = ""),
            PostData(title = "title", price = "5000", compound = "comp", delivery = false, userID = "", type = "", imageUrl = ""))
    var userData = UserData(userEmail = "name", userName = "email", userPhone = "777", userImageUrl = "photo", type = "master")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar!!.title = "Мой профиль"
        mView = inflater.inflate(R.layout.fragment_master_profile, container, false)

        mAdapter = PostAdapter(postArray, userData)
        val mLayoutManager = LinearLayoutManager(activity)
        mView!!.recyclerView.layoutManager = mLayoutManager
        mView!!.recyclerView.adapter = mAdapter

        setHasOptionsMenu(true)
        return mView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.extra -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
