package omirzhan.kz.keremetapp.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget.*
import omirzhan.kz.keremetapp.R

class ForgetActivity : AppCompatActivity() {

    val TAG = ForgetActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)

        restoreBtn.setOnClickListener {
            val email = emailEditText.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(applicationContext, "Напишите вашу почту", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d(TAG, "is Succesfull")
                    }
                }
            }
        }

        backBtn.setOnClickListener {
            finish()
        }
    }
}
