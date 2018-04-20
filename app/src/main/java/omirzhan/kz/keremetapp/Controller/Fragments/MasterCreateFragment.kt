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
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.ClipData
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_master_create.*
import nextstep.kz.tazalyk.adapter.RecyclerItemClickListener
import omirzhan.kz.keremetapp.Adapters.ImageAdapter
import omirzhan.kz.keremetapp.Extension.Const
import omirzhan.kz.keremetapp.Model.PostData
import java.io.FileDescriptor
import java.io.IOException


@Suppress("DEPRECATION")
class MasterCreateFragment : Fragment() {

    var mView: View? = null
    var progressDialog: ProgressDialog? = null
    private val TAG = MasterCreateFragment::class.java.simpleName
    val types = arrayOf("Торт", "Кекс", "Капкейк", "Бисквит")
    val imageArray: MutableList<Bitmap> = mutableListOf()
    val PICK_IMAGE_MULTIPLE = 200

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

            progressDialog = ProgressDialog(activity)
            progressDialog!!.setTitle("Загрузка...")
            progressDialog!!.setMessage("Пожалуйста подождите...")

            val horizontalLayoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL, false)
            mView!!.recyclerView.layoutManager = horizontalLayoutManager
            mView!!.recyclerView.adapter = ImageAdapter(imageArray = imageArray)

            mView!!.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(
                    activity as AppCompatActivity,
                    mView!!.recyclerView,
                    object: RecyclerItemClickListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            pickImages()
                        }

                        override fun onItemLongClick(view: View?, position: Int) {

                        }

                    }
            ))

            mView!!.createBtn.setOnClickListener {
                val title = mView!!.titleEditText.text.toString()
                val compound = mView!!.compoundEditText.text.toString()
                val type = mView!!.spinner.selectedItem.toString()
                val price = mView!!.priceEditText.text.toString()
                val delivery = mView!!.deliverySwitch.isChecked

                if (title.isEmpty() || compound.isEmpty() || type.isEmpty() || price.isEmpty()) {
                    Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
                } else {
                    progressDialog!!.show()
                    createPost(title, compound, type, price, delivery)
                }

            }
        }
        Log.d(TAG, "onCreateView")


        return mView
    }

    private fun createPost(title: String, compound: String, type: String, price: String, delivery: Boolean) {
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val postData = PostData(title,price, compound, delivery, type,"", userID)
        FirebaseDatabase.getInstance().reference.child("posts").push().setValue(postData) { p0, p1 ->
            if (p0 != null) {
                Log.d(TAG, p0.message)
                progressDialog!!.hide()
            } else {
                progressDialog!!.hide()
                uploadImages(p1.key)
                print(p1.key)
                Toast.makeText(activity, "Succes bro", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadImages(postID: String) {

    }

    private fun pickImages() {
        val intent = Intent()
        intent.type = "image/*"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE)
    }

    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        return try {
            val parcelFileDescriptor = activity!!.contentResolver.openAssetFileDescriptor(selectedFileUri, "r")
            val fileDescriptor = parcelFileDescriptor.fileDescriptor;
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            image
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
        // When an Image is picked
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                && null != data && null != data.clipData) {

            val mClipData = data.clipData
            val count = mClipData.itemCount
            imageArray.clear()
            for (i in 0 until mClipData.itemCount) {
                val bitmap = uriToBitmap(mClipData.getItemAt(i).uri)
                if (bitmap != null) imageArray.add(bitmap)
            }
            mView!!.recyclerView.adapter.notifyDataSetChanged()
        } else {
            imageArray.clear()
            mView!!.recyclerView.adapter.notifyDataSetChanged()
            Toast.makeText(activity, "You haven't picked any Image",
                    Toast.LENGTH_LONG).show()
        }
    } catch (e: Exception) {
        Toast.makeText(activity, "Error: Something went wrong " + e.message, Toast.LENGTH_LONG)
                .show()
            Log.d(TAG, e.localizedMessage)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
