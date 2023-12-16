package com.example.mypencilbook.ui

import android.animation.Animator
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypencilbook.R
import com.example.mypencilbook.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        binding.animationView.playAnimation()

        val sharedPreferences = requireActivity().getSharedPreferences("mypencilboolpref" , MODE_PRIVATE)
        val id = sharedPreferences.getString("id" , "")
        
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator) {

            }

            override fun onAnimationEnd(p0: Animator) {
                if (id != null) {
                    if (id.isNotEmpty()){
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

                    }else{
                        findNavController().navigate(R.id.action_splashFragment_to_signUpFragment)
                    }
                }
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })






        return binding.root
    }

}