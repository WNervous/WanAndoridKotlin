package com.wys.wankotlinpractice.knowledge.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.wys.wankotlinpractice.knowledge.mvp.model.Children
import com.wys.wankotlinpractice.knowledge.mvp.view.fragment.KnowledgeDetailItemFragment

class KnowledgePagerAdapter(
    fragmentManager: FragmentManager?,
    private val list: List<Children>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        return KnowledgeDetailItemFragment.newInstance(list[p0])
    }

    override fun getCount(): Int = if (list.isEmpty()) 0 else list.size

}