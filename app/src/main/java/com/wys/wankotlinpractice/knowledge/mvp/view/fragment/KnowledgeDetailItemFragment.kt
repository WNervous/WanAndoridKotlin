package com.wys.wankotlinpractice.knowledge.mvp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.knowledge.adapter.KnowledgeItemAdapter
import com.wys.wankotlinpractice.knowledge.mvp.contract.KnowledgeDetailContract
import com.wys.wankotlinpractice.knowledge.mvp.model.Children
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgePointBean
import com.wys.wankotlinpractice.knowledge.mvp.presenter.KnowledgeDetailPresenter
import kotlinx.android.synthetic.main.fragment_home.*

class KnowledgeDetailItemFragment : BaseFragment(), KnowledgeDetailContract.View {

    private lateinit var knowledgeDetailPresenter: KnowledgeDetailPresenter
    lateinit var children: Children
    private var list = mutableListOf<KnowledgePointBean>()
    private lateinit var adapter: KnowledgeItemAdapter

    override fun getContentViewId(): Int = R.layout.fragment_item_knowledge

    override fun init(savedInstanceState: Bundle?) {
        children = arguments?.getSerializable(KEY_ITEM_CHILDREN) as Children
        knowledgeDetailPresenter = KnowledgeDetailPresenter(this)
        knowledgeDetailPresenter.getKnowledgeDetail(0, children.id, false)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = KnowledgeItemAdapter(R.layout.item_detail_layout, list)
        recycleView.adapter = adapter
    }

    override fun isActive(): Boolean = isAdded

    override fun showKnowledgeDetail(knowledgeDetailBean: KnowledgeDetailBean) {
        list.addAll(knowledgeDetailBean.datas)
        adapter.notifyDataSetChanged()
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