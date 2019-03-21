package com.wys.wankotlinpractice.knowledge.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.wys.wankotlinpractice.knowledge.mvp.model.Children
import com.wys.wankotlinpractice.knowledge.mvp.view.fragment.KnowledgeItemFragment

class KnowledgePagerAdapter(
    private val fragmentManager: FragmentManager,
    private val itemCount: Int,
    private val list: List<Children>
) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(p0: Int): Fragment {
        return KnowledgeItemFragment()
    }

    override fun getCount(): Int = itemCount

}