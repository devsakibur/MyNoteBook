package com.example.mypencilbook.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mypencilbook.ui.ExpenceFragment
import com.example.mypencilbook.ui.NotesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager , lifecycle: Lifecycle) :FragmentStateAdapter(fragmentManager , lifecycle) {
    override fun getItemCount(): Int {
        //return the number of fragment
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> NotesFragment()
            1 -> ExpenceFragment()
            else -> throw IllegalArgumentException("Invalid Position of Fragment")
        }
    }
}