package com.wys.wankotlinpractice.project.presenter

import com.wys.wankotlinpractice.home.mvvm.model.ArticleBean
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import com.wys.wankotlinpractice.project.contract.ProjectDetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectDetailPresenter(val view: ProjectDetailContract.View) : ProjectDetailContract.Presenter {

    private var page = 0
    private var over = false

    override fun getDetailProjectItem(cid: Int, refresh: Boolean) {
        if (refresh) {
            page = 0
            over = false
        } else {
            if (over) {
                view.noMoreData()
                return
            }
            page++
        }

        ApiService.sApi.wanApi().getProjectDetails(page, cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<ArticleBean>() {
                override fun onSuccess(t: CommonResponse<ArticleBean>) {
                    over = t.data.over
                    view.showDetailProjectItem(t.data.datas, refresh)
                }

                override fun onFailure(e: Throwable) {
                    view.onFailed(refresh)
                }

            })
    }

    override fun onDestroty() {

    }
}