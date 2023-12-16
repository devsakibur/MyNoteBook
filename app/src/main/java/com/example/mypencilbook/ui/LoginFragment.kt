package com.example.mypencilbook.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mypencilbook.R
import com.example.mypencilbook.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        val sharedPreferences =
            requireActivity().getSharedPreferences("mypencilboolpref", Context.MODE_PRIVATE)
        val password = sharedPreferences.getString("password", "")
        val img = sharedPreferences.getString("img", "")
        val name = sharedPreferences.getString("name", "")



        binding.userImg.setImageBitmap(img?.let { base64ToBitmap(it) })


        binding.submitBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("img" ,img)
            bundle.putSerializable("name",name)

            if (binding.userPassword.text.toString() == password) {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment , bundle)
            } else {
                Toast.makeText(requireContext(), "Invalid Password", Toast.LENGTH_SHORT).show()
            }
    }

        return binding.root
    }



    private fun base64ToBitmap(base64String: String): Bitmap? {
        try {
            val decodedByteArray: ByteArray = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


}