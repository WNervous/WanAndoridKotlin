package com.wys.wankotlinpractice.home.mvp.presenter

import com.socks.library.KLog
import com.wys.wankotlinpractice.home.mvp.contract.HomeContract
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.mvp.model.Banner
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(val view: HomeContract.View) : HomeContract.Presenter {

    var currentPager = 0
    var pagerCount = 1

    override fun getBanner() {
        ApiService.sApi.wanApi().getHomeBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<List<Banner>>() {
                override fun onSuccess(t: CommonResponse<List<Banner>>) {
                    view.showBanners(t.data)
                }

                override fun onFailure(e: Throwable) {
                    KLog.e(e.toString())
                    view.bannerError()
                }
            })
    }

    override fun getArticle() {
        ApiService.sApi.wanApi().getHomeArticle(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<ArticleBean>() {
                override fun onSuccess(t: CommonResponse<ArticleBean>) {
                    val articleBean = t.data
                    pagerCount = articleBean.pageCount
                    currentPager = articleBean.curPage
                    view.showArticles(articleBean)
                }

                override fun onFailure(e: Throwable) {
                    KLog.e(e.toString())
                    view.articleError()
                }

            })
    }


    override fun loadMoreArticle() {
        if (currentPager >= pagerCount) {
            view.noMoreArticle()
            return
        }
        ApiService.sApi.wanApi().getHomeArticle(currentPager)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<ArticleBean>() {
                override fun onSuccess(t: CommonResponse<ArticleBean>) {
                    val articleBean = t.data
                    currentPager = articleBean.curPage
                    view.showArticles(articleBean)
                }

                override fun onFailure(e: Throwable) {
                    KLog.e(e.toString())
                    view.articleError()
                }

            })
    }

    override fun onDestroty() {

    }

}