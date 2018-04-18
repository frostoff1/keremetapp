package omirzhan.kz.keremetapp.Controller.Fragments


import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_master_create.view.*

import omirzhan.kz.keremetapp.R
import android.app.Activity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_master_create.*


class MasterCreateFragment : Fragment() {

    var mView: View? = null
    private val TAG = MasterCreateFragment::class.java.simpleName
    val types = arrayOf("Торт", "Кекс", "Капкейк", "Бисквит")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        (activity as AppCompatActivity).supportActionBar!!.title = "Публикация"

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_master_create, container, false)
            var spinnerAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, types)
            spinnerAdapter
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            mView!!.spinner.adapter = spinnerAdapter
        }


        Log.d(TAG, "onCreateView")
        return mView
    }




}
