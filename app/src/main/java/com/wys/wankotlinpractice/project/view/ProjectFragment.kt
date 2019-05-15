package com.wys.wankotlinpractice.project.view

import android.os.Bundle
import android.view.View
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.project.adapter.ProjectPagerAdapter
import com.wys.wankotlinpractice.project.contract.ProjectContract
import com.wys.wankotlinpractice.project.model.ProjectBean
import com.wys.wankotlinpractice.project.presenter.ProjectPresenter
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : BaseFragment(), ProjectContract.View {
    private lateinit var mAdapter: ProjectPagerAdapter
    private lateinit var mPresenter: ProjectPresenter
    override fun getContentViewId(): Int = R.layout.fragment_project

    override fun init(savedInstanceState: Bundle?) {
        mPresenter = ProjectPresenter(this)
        mPresenter.getProject()
        mAdapter = ProjectPagerAdapter(childFragmentManager)
        viewPager.adapter = mAdapter
    }

    //////////////////////////////////////////////////////////////////////////////
    // override
    //////////////////////////////////////////////////////////////////////////////

    override fun showProject(list: MutableList<ProjectBean>) {
        slidingTabLayout.visibility = View.VISIBLE
        mAdapter.setData(list)
        slidingTabLayout.setupWithViewPager(viewPager)
    }

    override fun onFailed() {
        slidingTabLayout.visibility = View.GONE
    }

    override fun isActive(): Boolean = isAdded

}