package com.wys.wankotlinpractice.home.mvp.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.mvp.model.Banner

class HomeContract {
    interface View : IView {
        fun showBanners(banner: List<Banner>)
        fun showArticles(articleBean: ArticleBean)
    }

    interface Presenter : IPresenter {
        fun getBanner()
        fun getArticle()
    }
}