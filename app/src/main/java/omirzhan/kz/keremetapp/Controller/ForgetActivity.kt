package omirzhan.kz.keremetapp.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_forget.*
import omirzhan.kz.keremetapp.R

class ForgetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)

        backBtn.setOnClickListener {
            finish()
        }
    }
}
