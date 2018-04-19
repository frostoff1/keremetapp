package omirzhan.kz.keremetapp.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import omirzhan.kz.keremetapp.R

class RegisterActivity : AppCompatActivity() {

    private val TAG = RegisterActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        backBtn.setOnClickListener {
            this.finish()
        }

        registerBtn.setOnClickListener {
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword("mike@mail.com", "123123")
                    .addOnCompleteListener {
                        if (it.exception != null) {
                            Log.d(TAG, it.exception!!.message)
                        }
                        if (it.isSuccessful) {
                            Log.d(TAG, it.result.user.uid)
                        }
                    }
        }
    }
}
