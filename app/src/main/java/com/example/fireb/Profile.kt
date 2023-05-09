package com.example.fireb

import User
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fireb.Config.hideDialog
import com.example.fireb.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.ref.PhantomReference

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var dialog: Dialog
    private var backPressedTime: Long = 0
    private lateinit var toast: Toast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        binding.saveBtn.setOnClickListener {
            showProgressBar()
            val name = binding.userName.text.toString()
//            val image = imageUri.toString()
            val email = binding.userEmail.text.toString()
            val location = binding.userCity.text.toString()
            val aadharNumber = binding.userAadhar.text.toString()

            val user = User(name, email, location, aadharNumber)
            if (uid != null) {

                databaseReference.child(uid).setValue(user).addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        databaseReference.child(uid).setValue(user).addOnCompleteListener {
                            if(it.isSuccessful){
                                uploadProfilePic()
                            }
                            else{
                                hideProgress()
                                Toast.makeText(this@Profile,"",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
           val intent = Intent(this,ProfileResult::class.java)
            startActivity(intent)
        }

    }

    private fun uploadProfilePic() {
        imageUri = Uri.parse("android.resource://$packageName/${R.drawable.police}")
        storageReference = FirebaseStorage.getInstance().getReference("users/"+auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {
            hideProgress()
            Toast.makeText(this@Profile,"Profile Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            hideProgress()
            Toast.makeText(this@Profile,"Failed to Upload Profile",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProgressBar() {
        dialog = Dialog(this@Profile)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
    private fun hideProgress(){
        dialog.dismiss()
    }

}

