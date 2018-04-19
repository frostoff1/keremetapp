package omirzhan.kz.keremetapp.Controller

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_master.*
import omirzhan.kz.keremetapp.Controller.Fragments.MasterCreateFragment
import omirzhan.kz.keremetapp.Controller.Fragments.MasterPostsFragment
import omirzhan.kz.keremetapp.Controller.Fragments.MasterProfileFragment
import omirzhan.kz.keremetapp.R

class MasterActivity : AppCompatActivity() {

    lateinit var fragment: Fragment
    private lateinit var fragmentTransaction: FragmentTransaction
    lateinit var mMenu: Menu

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = MasterPostsFragment()
                switchFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_create -> {
                fragment = MasterCreateFragment()
                switchFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                fragment = MasterProfileFragment()
                switchFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fragment = MasterPostsFragment()
        switchFragment(fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    private fun switchFragment(fragment: Fragment) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}
