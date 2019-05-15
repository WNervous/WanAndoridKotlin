package com.wys.wankotlinpractice.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, fragment: MutableList<Fragment>) :
    FragmentStatePagerAdapter(fragmentManager) {

    var fragments: MutableList<Fragment> = fragment

    override fun getItem(position: Int): Fragment {
        Log.d("viewpager", "$position")
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}