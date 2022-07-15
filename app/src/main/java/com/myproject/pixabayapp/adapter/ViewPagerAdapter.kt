package com.myproject.pixabayapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myproject.pixabayapp.view.ViewpagerFragment
import com.myproject.pixabayapp.constant.Constants.ARG_OBJECT
import com.myproject.pixabayapp.data.ImageData

class ViewPagerAdapter(fragment: Fragment, val list: List<ImageData>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewpagerFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}