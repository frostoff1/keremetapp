package omirzhan.kz.keremetapp.Controller.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import omirzhan.kz.keremetapp.R

class MasterProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar!!.title = "Мой профиль"
        return inflater.inflate(R.layout.fragment_master_profile, container, false)
    }


}
