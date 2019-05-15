package com.wys.wankotlinpractice.project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wys.wankotlinpractice.project.model.ProjectBean
import com.wys.wankotlinpractice.project.view.ProjectDetailFragment

class ProjectPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private var mList = mutableListOf<ProjectBean>()
    override fun getItem(p0: Int): Fragment {
        val projectBean = mList[p0]
        return ProjectDetailFragment.newInstance(projectBean.id)
    }

    override fun getCount(): Int = mList.size


    fun setData(list: MutableList<ProjectBean>) {
        mList.clear()
        mList = list
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position].name
    }
}