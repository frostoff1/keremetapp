package omirzhan.kz.keremetapp.Controller

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import omirzhan.kz.keremetapp.Model.UserData
import omirzhan.kz.keremetapp.R

@Suppress("DEPRECATION")
class LoginActivity : Activity() {

    val TAG = LoginActivity::class.java.simpleName
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Загрузка...")
        progressDialog!!.setMessage("Пожалуйста подождите...")


        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        forgetBtn.setOnClickListener {
            val intent = Intent(this, ForgetActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog!!.show()
                login(email, password)
            }

        }


    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            val userID = FirebaseAuth.getInstance().currentUser!!.uid
            progressDialog!!.show()
            getUserType(userID)
        }
    }
    fun navigateToClient() {
        val intent = Intent(this, ClientActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun navigateToMaster() {
        val intent = Intent(this, MasterActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d(TAG, "login success")
                        val userID = it.result.user.uid
                        getUserType(userID)
                    } else {
                        Log.d(TAG, "login fail" + it.exception!!.message)
                    }
                }
    }


        fun getUserType(userID: String) {
            FirebaseDatabase.getInstance().reference.child("users")
                    .child(userID).addListenerForSingleValueEvent(object: ValueEventListener{
                        override fun onCancelled(p0: DatabaseError?) {
                            Log.d(TAG, "onCancelled" + p0.toString())
                            progressDialog!!.dismiss()
                        }

                        override fun onDataChange(p0: DataSnapshot?) {
                            Log.d(TAG, "onDataChange" + p0.toString())
                            val cUser = p0!!.getValue(UserData::class.java)
                            Log.d(TAG, cUser!!.userName)
                            progressDialog!!.dismiss()
                            if (cUser.type == "master") {
                                navigateToMaster()
                            } else {
                                navigateToClient()
                            }
                        }
                    })

        }
}
