package com.example.demoroomdatabasehilt

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demoroomdatabasehilt.adapter.UserAdapter
import com.example.demoroomdatabasehilt.databinding.ActivityMainBinding
import com.example.demoroomdatabasehilt.databinding.CustomDialogboxBinding
import com.example.demoroomdatabasehilt.model.UserModel
import com.example.demoroomdatabasehilt.viewmodel.MainViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()
    var name: String? = null
    var uri: Uri? = null
    var image: Bitmap? = null
    lateinit var userAdapter: UserAdapter
    lateinit var customDialogboxBinding: CustomDialogboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userAdapter = UserAdapter() {
            alertDialogBox(it)
            customDialogboxBinding.editTextName.setText(it.name)
            customDialogboxBinding.editPhoneNumber.setText(it.contact_number)
            customDialogboxBinding.imgProfile.setImageBitmap(it.image)

        }
        activityMainBinding.listRecyclerView.adapter = userAdapter

        mainViewModel.getUser.observe(this) { response ->
            userAdapter.addItems(response as ArrayList<UserModel>)
        }

        activityMainBinding.ButtonFloating.setOnClickListener {
            alertDialogBox()
        }

    }

    private fun alertDialogBox(contact: UserModel? = null) {
        val dialog = Dialog(this)
        customDialogboxBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.custom__dialogbox,
            null,
            false
        )
        dialog.setContentView(customDialogboxBinding.getRoot())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(650, 1000)
        dialog.show()

        if (contact == null) {
            customDialogboxBinding.buttonSave.setOnClickListener {
                //saveData()


                val userName = customDialogboxBinding.editTextName.text.toString().trim()
                val userNumber = customDialogboxBinding.editPhoneNumber.text.toString().trim()
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userNumber)) {
                    if(image!=null) {
                        val user = UserModel(0, userName, userNumber, image!!)
                        mainViewModel.insertData(user)
                        Toast.makeText(this, "Insert Data User", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    else
                    {
                        Toast.makeText(this, "Please Select User Profile", Toast.LENGTH_SHORT).show()

                    }


                } else {
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                }



            }
        }
        else {

            customDialogboxBinding.buttonSave.text = "Update Record"
            customDialogboxBinding.buttonSave.setOnClickListener {

                val userName = customDialogboxBinding.editTextName.text.toString().trim()
                val userNumber = customDialogboxBinding.editPhoneNumber.text.toString().trim()
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userNumber)) {
                    if(image!=null) {
                        val user = UserModel(contact.id, userName, userNumber, image!!)
                        mainViewModel.updateRecord(user)
                        Toast.makeText(this, "Update Data User", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    else{
                        Toast.makeText(this, "Please Select User Profile", Toast.LENGTH_SHORT).show()

                    }

                } else {
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                }





            }
        }






        customDialogboxBinding.imgProfile.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()

        }
        customDialogboxBinding.iconId.setOnClickListener {
            dialog.dismiss()
            customDialogboxBinding.editTextName.setText("")
            customDialogboxBinding.editPhoneNumber.setText("")
            customDialogboxBinding.imgProfile.setImageBitmap(null)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            uri = data?.data!!
            image = MediaStore.Images.Media.getBitmap(this.contentResolver, this.uri) as Bitmap?
            customDialogboxBinding.imgProfile.setImageBitmap(image)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


}