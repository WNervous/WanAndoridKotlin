package com.wys.wankotlinpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.wys.wankotlinpractice.adapter.ViewPagerAdapter
import com.wys.wankotlinpractice.base.BaseActivity
import com.wys.wankotlinpractice.home.view.HomeFragment
import com.wys.wankotlinpractice.knowledge.mvp.view.fragment.KnowledgeFragment
import com.wys.wankotlinpractice.nav.view.NavFragment
import com.wys.wankotlinpractice.project.view.ProjectFragment
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
            when (it.itemId) {
                R.id.home -> viewPager.currentItem = 0
                R.id.knowledge -> viewPager.currentItem = 1
                R.id.navigation -> viewPager.currentItem = 2
                R.id.project -> viewPager.currentItem = 3
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.home
    }

    private fun addFragments() {
        fragments = mutableListOf()
        fragments.add(HomeFragment())
        fragments.add(KnowledgeFragment())
        fragments.add(NavFragment())
        fragments.add(ProjectFragment())
        mPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
    }

    private fun initViewPager() {
        viewPager.adapter = mPagerAdapter
        viewPager.currentItem = 0
        viewPager.offscreenPageLimit = 3
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}