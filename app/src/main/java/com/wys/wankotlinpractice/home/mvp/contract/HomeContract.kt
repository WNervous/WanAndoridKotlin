package com.wys.wankotlinpractice.home.mvp.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.home.mvvm.model.ArticleBean
import com.wys.wankotlinpractice.home.mvvm.model.Banner

class HomeContract {
    interface View : IView {
        fun showBanners(banner: List<Banner>)
        fun showArticles(articleBean: ArticleBean, refresh: Boolean)
        fun noMoreArticle()
        fun articleError()
        fun bannerError()
    }

    interface Presenter : IPresenter {
        fun getBanner()
        fun getArticle(refresh: Boolean)
    }
}