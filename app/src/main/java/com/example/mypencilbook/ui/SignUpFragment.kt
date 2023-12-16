package com.example.mypencilbook.ui

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mypencilbook.R
import com.example.mypencilbook.databinding.FragmentSignUpBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class SignUpFragment : Fragment() {
private lateinit var binding: FragmentSignUpBinding
private lateinit var myImg:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)




        binding.addImg.setOnClickListener{
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }


        binding.submitBtn.setOnClickListener {

            val id = "111"
            val name = binding.userName.text
            val password = binding.userPassword.text
            val img = myImg


            val sharedPreferences = requireActivity().getSharedPreferences("mypencilboolpref" , Context.MODE_PRIVATE).edit()
            sharedPreferences.putString("id" , id)
            sharedPreferences.putString("name" , name.toString())
            sharedPreferences.putString("password" , password.toString())
            sharedPreferences.putString("img" , img)
            sharedPreferences.apply()


            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }









        return binding.root
    }

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                binding.userImg.setImageURI(imgUri)
                myImg = imgUri?.let { it1 -> getImageAsBase64(requireContext() , it1) }.toString()
            }
        }

    private fun getImageAsBase64(context: Context, imgUri: Uri): String? {
        val contentResolver: ContentResolver = context.contentResolver

        try {
            // Step 1: Open an InputStream for the Uri
            val inputStream: InputStream? = contentResolver.openInputStream(imgUri)

            // Step 2: Convert InputStream to Bitmap
            val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)

            // Step 3: Convert Bitmap to byte array
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

            // Step 4: Encode byte array to Base64 string
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }




}