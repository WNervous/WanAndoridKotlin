package com.wys.wankotlinpractice.nav.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.nav.adapter.NavAdapter
import com.wys.wankotlinpractice.nav.contract.NavContract
import com.wys.wankotlinpractice.nav.model.NavBean
import com.wys.wankotlinpractice.nav.presenter.NavPrestenter
import kotlinx.android.synthetic.main.fragment_nav.*

class NavFragment : BaseFragment(), NavContract.View {

    private lateinit var navPrestenter: NavPrestenter
    private lateinit var adapter: NavAdapter
    private val list = listOf<NavBean>()
    override fun getContentViewId(): Int = R.layout.fragment_nav

    override fun init(savedInstanceState: Bundle?) {
        navPrestenter = NavPrestenter(this)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = NavAdapter(R.layout.item_nav, list)
        recycleView.adapter = adapter

        navPrestenter.getNav()
    }

    override fun showNav(list: List<NavBean>) {
        adapter.setNewData(list)
    }

    override fun isActive(): Boolean = isAdded
}