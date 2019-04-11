package com.wys.wankotlinpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.wys.wankotlinpractice.adapter.ViewPagerAdapter
import com.wys.wankotlinpractice.base.BaseActivity
import com.wys.wankotlinpractice.home.view.HomeFragment
import com.wys.wankotlinpractice.knowledge.mvp.view.fragment.KnowledgeFragment
import com.wys.wankotlinpractice.nav.view.NavFragment
import com.wys.wankotlinpractice.project.view.ProjectFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mPagerAdapter: ViewPagerAdapter
    private lateinit var fragments: MutableList<Fragment>
    override fun contentLayoutId(): Int = R.layout.activity_main
    override fun init(savedInstanceState: Bundle?) {
        init()
        addFragments()
        initViewPager()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

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

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}