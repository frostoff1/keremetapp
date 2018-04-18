package omirzhan.kz.keremetapp.Adapters

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import omirzhan.kz.keremetapp.Model.PostData
import omirzhan.kz.keremetapp.Model.UserData
import omirzhan.kz.keremetapp.R

class PostAdapter(postArray: List<PostData>): RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    var postArray = postArray
    var userData: UserData? = null
    val ITEM_TYPE_CELL = 0
    val ITEM_TYPE_HEADER = 1
    val ITEM_TYPE = 2

    constructor(postArray: List<PostData>, userData: UserData) : this(postArray) {
       this.userData = userData
    }
    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(post: PostData) {

        }
        fun bindHeader(userData: UserData) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) : MyViewHolder? {
        if (viewType == ITEM_TYPE_CELL) {
            var view =LayoutInflater.from(parent!!.context).inflate(R.layout.row_post, parent, false)
            return MyViewHolder(view)
        } else if (viewType == ITEM_TYPE_HEADER) {
            var view =LayoutInflater.from(parent!!.context).inflate(R.layout.row_post_header, parent, false)
            return MyViewHolder(view)
        } else {
            var view =LayoutInflater.from(parent!!.context).inflate(R.layout.row_post_cell, parent, false)
            return MyViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return postArray.size
    }

    override fun getItemViewType(position: Int): Int {
        if (userData != null) {
            if (position == 0) return ITEM_TYPE_HEADER
            return ITEM_TYPE
        }
        return ITEM_TYPE_CELL
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val itemType = getItemViewType(position)
        if (holder != null) {
            if (itemType == ITEM_TYPE_CELL) {
                holder.bindItems(postArray[position])
            } else {
                holder.bindHeader(this!!.userData!!)
            }

        }
    }

}