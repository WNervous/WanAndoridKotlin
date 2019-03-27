package com.wys.wankotlinpractice.nav.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.nav.model.NavBean

class NavContract {
    interface View : IView {
        fun showNav(list: List<NavBean>)
    }

    interface Presenter : IPresenter {
        fun getNav()
    }
}