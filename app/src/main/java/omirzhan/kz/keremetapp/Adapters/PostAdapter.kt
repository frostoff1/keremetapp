package omirzhan.kz.keremetapp.Adapters

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import omirzhan.kz.keremetapp.Model.PostData
import omirzhan.kz.keremetapp.R

class PostAdapter(postArray: List<PostData>): RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    var postArray = postArray
    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(post: PostData) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) : MyViewHolder? {
        var view =LayoutInflater.from(parent!!.context).inflate(R.layout.row_post, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        if (holder != null) {
            holder.bindItems(postArray[position])
        }
    }

}