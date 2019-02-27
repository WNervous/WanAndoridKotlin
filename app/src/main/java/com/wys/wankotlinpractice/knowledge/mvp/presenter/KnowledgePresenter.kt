package com.wys.wankotlinpractice.knowledge.mvp.presenter

import com.wys.wankotlinpractice.knowledge.mvp.contract.KonwledgeContract
import com.wys.wankotlinpractice.net.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class KnowledgePresenter(val view: KonwledgeContract.View) : KonwledgeContract.Presenter {
    override fun getKnowledgeSeries() {
        ApiService.sApi.wanApi().getKnowledgeService()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.showKnowledgeSeries(it.data)
            }
    }

    override fun onDestroty() {

    }



}