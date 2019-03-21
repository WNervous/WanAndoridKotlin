package com.wys.wankotlinpractice.knowledge.mvp.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeBean

class KnowledgeContract {
    interface View : IView {
        fun showKnowledgeSeries(knowledge: List<KnowledgeBean>)
    }

    interface Presenter : IPresenter {
        fun getKnowledgeSeries()
    }
}