package com.wys.wankotlinpractice.project.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.wys.wankotlinpractice.project.model.ProjectBean
import com.wys.wankotlinpractice.project.view.ProjectDetailFragment

class ProjectPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var mList = mutableListOf<ProjectBean>()
    override fun getItem(p0: Int): Fragment {
        val projectBean = mList[p0]
        return ProjectDetailFragment.newInstance(projectBean.id)
    }

    override fun getCount(): Int = mList.size


    public fun setData(list: MutableList<ProjectBean>) {
        mList.clear()
        mList = list
        notifyDataSetChanged()
    }
}