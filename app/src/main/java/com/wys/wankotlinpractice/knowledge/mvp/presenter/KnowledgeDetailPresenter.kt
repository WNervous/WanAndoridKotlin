package com.wys.wankotlinpractice.knowledge.mvp.presenter

import com.wys.wankotlinpractice.knowledge.mvp.contract.KnowledgeDetailContract
import com.wys.wankotlinpractice.net.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class KnowledgeDetailPresenter(val view: KnowledgeDetailContract.View) : KnowledgeDetailContract.Presenter {

    private lateinit var subscribe: Disposable
    private var pagerId = 0
    override fun onDestroty() {
        if (!subscribe.isDisposed) {
            subscribe.dispose()
        }
    }

    override fun getKnowledgeDetail(pagerId: Int, cid: Int, refresh: Boolean) {
        if (refresh) {
            this.pagerId = 0
        }
        subscribe = ApiService.sApi.wanApi().getSeriesDetails(this.pagerId, cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.pagerId = it.data.curPager - 1
                view.showKnowledgeDetail(it.data)
            }
    }
}