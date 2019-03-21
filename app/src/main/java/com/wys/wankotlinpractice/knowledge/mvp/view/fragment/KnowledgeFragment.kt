package com.wys.wankotlinpractice.knowledge.mvp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.knowledge.adapter.KnowledgeAdapter
import com.wys.wankotlinpractice.knowledge.mvp.contract.KnowledgeContract
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeBean
import com.wys.wankotlinpractice.knowledge.mvp.presenter.KnowledgePresenter
import kotlinx.android.synthetic.main.fragment_knowledge.*

class KnowledgeFragment : BaseFragment(), KnowledgeContract.View {


    private lateinit var knowledgePresenter: KnowledgePresenter
    private lateinit var knowledgeAdapter: KnowledgeAdapter
    private val datas: List<KnowledgeBean> = listOf()

    override fun getContentViewId(): Int = R.layout.fragment_knowledge

    override fun init(savedInstanceState: Bundle?) {
        knowledgePresenter = KnowledgePresenter(this)
        knowledgeAdapter = KnowledgeAdapter(R.layout.item_knowledge_tree, datas)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = knowledgeAdapter
        knowledgePresenter.getKnowledgeSeries()
    }

    override fun showKnowledgeSeries(knowledge: List<KnowledgeBean>) {
        knowledgeAdapter.setNewData(knowledge)
    }

    override fun isActive() = isAdded

}