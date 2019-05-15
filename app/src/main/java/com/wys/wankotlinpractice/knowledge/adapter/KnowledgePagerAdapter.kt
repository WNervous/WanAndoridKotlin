package com.wys.wankotlinpractice.knowledge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wys.wankotlinpractice.knowledge.mvp.model.Children
import com.wys.wankotlinpractice.knowledge.mvp.view.fragment.KnowledgeDetailItemFragment

class KnowledgePagerAdapter(
    fragmentManager: FragmentManager,
    private val list: List<Children>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        return KnowledgeDetailItemFragment.newInstance(list[p0])
    }

    override fun getCount(): Int = if (list.isEmpty()) 0 else list.size
    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }
}