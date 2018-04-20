package omirzhan.kz.keremetapp.Controller

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import omirzhan.kz.keremetapp.Model.UserData
import omirzhan.kz.keremetapp.R

@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity() {

    private val TAG = RegisterActivity::class.java.simpleName
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Загрузка...")
        progressDialog!!.setMessage("Пожалуйста подождите...")

        backBtn.setOnClickListener {
            this.finish()
        }

        registerBtn.setOnClickListener {
            Log.d(TAG, "registerBtn clicked")
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val name = nameEditText.text.toString()
            val type = if(regiterSwitch.isChecked) "master" else  "client"

            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || type.isEmpty()) {
                Toast.makeText(applicationContext, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog!!.show()
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.exception != null) {
                                Log.d(TAG, it.exception!!.message)
                                progressDialog!!.hide()
                            }
                            if (it.isSuccessful) {
                                Log.d(TAG, it.result.user.uid)
                                val userID = it.result.user.uid
                                val userData = UserData(name, "", email, "", type)
                                FirebaseDatabase.getInstance().reference.child("users")
                                        .child(userID).setValue(userData) { p0, p1 ->
                                            if (p0 != null) {
                                                Log.d(TAG, p0.message)
                                                progressDialog!!.hide()
                                            } else {
                                                progressDialog!!.hide()
                                                finish()
                                            }
                                        }

                            }

                        }
            }


        }
    }
}
