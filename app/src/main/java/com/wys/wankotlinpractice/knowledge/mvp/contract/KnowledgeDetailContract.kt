package com.wys.wankotlinpractice.knowledge.mvp.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean

class KnowledgeDetailContract {

    interface View : IView {
        fun showRefreshKnowledgeDetail(knowledgeDetailBean: KnowledgeDetailBean)
        fun showLoadMoreKnowledgeDetail(knowledgeDetailBean: KnowledgeDetailBean)
        fun noMoreData()
        fun onFailed(refresh: Boolean)
    }

    interface Presenter : IPresenter {
        fun getKnowledgeDetail(cid: Int,refresh:Boolean)
    }

}