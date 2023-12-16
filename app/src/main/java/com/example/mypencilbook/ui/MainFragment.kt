package com.example.mypencilbook.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypencilbook.adapter.ViewPagerAdapter
import com.example.mypencilbook.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {
private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)



        val name = requireArguments().getString("name" , "")
        val img = requireArguments().getString("img" , "")

        binding.name.text = name
        binding.userImg.setImageBitmap(base64ToBitmap(img))


        val tabLayout = binding.tabLayout
        val viewPager =binding.viewPager


        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
        viewPager.adapter = adapter


        TabLayoutMediator(tabLayout , viewPager){tab , position ->
            when(position){
                0 ->tab.text =  "Notes"
                1 ->tab.text = "Expenses"
            }

        }.attach()





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