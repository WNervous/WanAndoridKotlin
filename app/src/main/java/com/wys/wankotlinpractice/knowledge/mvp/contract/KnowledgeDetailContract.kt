package com.wys.wankotlinpractice.knowledge.mvp.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean

class KnowledgeDetailContract {

    interface View : IView {
        fun showKnowledgeDetail(knowledgeDetailBean: KnowledgeDetailBean)
    }

    interface Presenter : IPresenter {
        fun getKnowledgeDetail(pagerId: Int, cid: Int,refresh:Boolean)
    }

}