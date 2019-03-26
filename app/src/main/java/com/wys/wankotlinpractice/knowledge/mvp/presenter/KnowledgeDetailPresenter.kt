package com.wys.wankotlinpractice.knowledge.mvp.presenter

import com.wys.wankotlinpractice.knowledge.mvp.contract.KnowledgeDetailContract
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class KnowledgeDetailPresenter(val view: KnowledgeDetailContract.View) : KnowledgeDetailContract.Presenter {

    private lateinit var subscribe: Disposable
    private var pagerId = 0
    private var pagerCount = 1
    override fun onDestroty() {
        if (!subscribe.isDisposed) {
            subscribe.dispose()
        }
    }

    override fun getKnowledgeDetail(cid: Int, refresh: Boolean) {
        if (refresh) {
            this.pagerId = 0
        } else {
            if (pagerId >= pagerCount) {
                view.noMoreData()
                return
            }
            pagerId++
        }

        ApiService.sApi.wanApi().getSeriesDetails(this.pagerId, cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<KnowledgeDetailBean>() {
                override fun onSuccess(t: CommonResponse<KnowledgeDetailBean>) {
                    pagerCount = t.data.pageCount
                    if (refresh) view.showRefreshKnowledgeDetail(t.data) else view.showLoadMoreKnowledgeDetail(t.data)
                }

                override fun onFailure(e: Throwable) {
                    view.onFailed(refresh)
                }

            })
    }
}