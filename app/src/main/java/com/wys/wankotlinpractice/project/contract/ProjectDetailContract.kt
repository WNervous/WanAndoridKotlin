package com.wys.wankotlinpractice.project.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.home.mvvm.model.ArticleBean

class ProjectDetailContract {

    interface View : IView {

        fun showDetailProjectItem(list: List<ArticleBean.Article>, refresh: Boolean)
        fun noMoreData()
        fun onFailed(refresh: Boolean)
    }

    interface Presenter : IPresenter {
        fun getDetailProjectItem(cid: Int, refresh: Boolean)
    }
}