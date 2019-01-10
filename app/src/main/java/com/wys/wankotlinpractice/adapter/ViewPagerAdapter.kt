package com.wys.wankotlinpractice.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log

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