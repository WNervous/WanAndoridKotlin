package com.wys.wankotlinpractice.home.mvp.presenter

import com.socks.library.KLog
import com.wys.wankotlinpractice.home.mvp.contract.HomeContract
import com.wys.wankotlinpractice.home.mvvm.model.ArticleBean
import com.wys.wankotlinpractice.home.mvvm.model.Banner
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(val view: HomeContract.View) : HomeContract.Presenter {

    var currentPager = 0
    var pagerCount = 1
    var over = false

    override fun getBanner() {
        ApiService.sApi.wanApi().getHomeBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<List<Banner>>() {
                override fun onSuccess(t: CommonResponse<List<Banner>>) {
                    view.showBanners(t.data)
                }

                override fun onFailure(e: Throwable) {
                    KLog.e(e)
                    view.bannerError()
                }
            })
    }


    override fun getArticle(refresh: Boolean) {
        if (refresh) {
            currentPager = 0
        } else {
            if (over) {
                view.noMoreArticle()
                return
            }
            currentPager++
        }
        ApiService.sApi.wanApi().getHomeArticle(currentPager)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<ArticleBean>() {
                override fun onSuccess(t: CommonResponse<ArticleBean>) {
                    val articleBean = t.data
                    over = t.data.over
                    view.showArticles(articleBean, refresh)
                }

                override fun onFailure(e: Throwable) {
                    KLog.e(e)
                    view.articleError()
                }

            })
    }

    override fun onDestroty() {

    }

}