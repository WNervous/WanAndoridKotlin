package com.wys.wankotlinpractice.knowledge.mvp.view.fragment

import android.os.Bundle
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.knowledge.mvp.contract.KnowledgeDetailContract
import com.wys.wankotlinpractice.knowledge.mvp.model.Children
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean
import com.wys.wankotlinpractice.knowledge.mvp.presenter.KnowledgeDetailPresenter

class KnowledgeDetailItemFragment : BaseFragment(), KnowledgeDetailContract.View {


    private lateinit var knowledgeDetailPresenter: KnowledgeDetailPresenter
    lateinit var children: Children
    override fun getContentViewId(): Int = R.layout.fragment_item_knowledge

    override fun init(savedInstanceState: Bundle?) {
        children = arguments?.getSerializable(KEY_ITEM_CHILDREN) as Children
        knowledgeDetailPresenter = KnowledgeDetailPresenter(this)
        knowledgeDetailPresenter.getKnowledgeDetail(0, children.id, false)
    }

    override fun isActive(): Boolean = isAdded

    override fun showKnowledgeDetail(knowledgeDetailBean: KnowledgeDetailBean) {


    }

    companion object {
        const val KEY_ITEM_CHILDREN = "KEY_ITEM_CHILDREN"
        fun newInstance(children: Children): KnowledgeDetailItemFragment {
            val knowledgeItemFragment = KnowledgeDetailItemFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_ITEM_CHILDREN, children)
            knowledgeItemFragment.arguments = bundle
            return knowledgeItemFragment
        }
    }

}