package com.wys.wankotlinpractice

import android.os.Bundle
import android.support.v4.app.Fragment
import com.wys.wankotlinpractice.adapter.ViewPagerAdapter
import com.wys.wankotlinpractice.base.BaseActivity
import com.wys.wankotlinpractice.home.view.HomeFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : BaseActivity() {

    private lateinit var mPagerAdapter: ViewPagerAdapter
    private lateinit var fragments: MutableList<Fragment>

    override fun contentLayoutId(): Int = R.layout.activity_main
    override fun init(savedInstanceState: Bundle?) {
        init()
        addFragments()
        initViewPager()
    }

    private fun init() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            toolbar.title = it.title
            viewPager.currentItem = it.order
            true
        }
        bottomNavigationView.selectedItemId = R.id.home
    }

    private fun addFragments() {
        fragments = mutableListOf()
        fragments.add(HomeFragment())
        fragments.add(HomeFragment())
        fragments.add(HomeFragment())
        fragments.add(HomeFragment())
        mPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
    }

    private fun initViewPager() {
        viewPager.adapter = mPagerAdapter
        viewPager.currentItem = 0
    }
}