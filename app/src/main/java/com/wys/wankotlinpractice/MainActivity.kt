package com.wys.wankotlinpractice

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.wys.wankotlinpractice.adapter.ViewPagerAdapter
import com.wys.wankotlinpractice.base.BaseActivity
import com.wys.wankotlinpractice.home.mvvm.view.HomeFragment
import com.wys.wankotlinpractice.knowledge.mvp.view.fragment.KnowledgeFragment
import com.wys.wankotlinpractice.nav.view.NavFragment
import com.wys.wankotlinpractice.project.view.ProjectFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


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
        toggle.drawerArrowDrawable.color = Color.WHITE
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
        val res = listOf(R.id.home, R.id.knowledge, R.id.navigation, R.id.project)
        viewPager.adapter = mPagerAdapter
        viewPager.currentItem = 0
        viewPager.offscreenPageLimit = 3
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.selectedItemId = res[position]
            }
        })
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