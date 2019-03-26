package com.wys.wankotlinpractice.knowledge.mvp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.knowledge.adapter.KnowledgeItemAdapter
import com.wys.wankotlinpractice.knowledge.mvp.contract.KnowledgeDetailContract
import com.wys.wankotlinpractice.knowledge.mvp.model.Children
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgePointBean
import com.wys.wankotlinpractice.knowledge.mvp.presenter.KnowledgeDetailPresenter
import kotlinx.android.synthetic.main.fragment_item_knowledge.*

class KnowledgeDetailItemFragment : BaseFragment(), KnowledgeDetailContract.View {

    private lateinit var knowledgeDetailPresenter: KnowledgeDetailPresenter
    lateinit var children: Children
    private var list = mutableListOf<KnowledgePointBean>()
    private lateinit var adapter: KnowledgeItemAdapter

    override fun getContentViewId(): Int = R.layout.fragment_item_knowledge

    override fun init(savedInstanceState: Bundle?) {
        children = arguments?.getSerializable(KEY_ITEM_CHILDREN) as Children
        knowledgeDetailPresenter = KnowledgeDetailPresenter(this)
        knowledgeDetailPresenter.getKnowledgeDetail(children.id, true)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = KnowledgeItemAdapter(R.layout.item_detail_layout, list)
        recycleView.adapter = adapter
        smartRefreshLayout.setRefreshHeader(MaterialHeader(context))
        smartRefreshLayout.setRefreshFooter(ClassicsFooter(context))
        smartRefreshLayout.setOnRefreshListener {
            knowledgeDetailPresenter.getKnowledgeDetail(children.id, true)
        }

        smartRefreshLayout.setOnLoadMoreListener {
            knowledgeDetailPresenter.getKnowledgeDetail(children.id, false)
        }
    }

    override fun isActive(): Boolean = isAdded

    override fun showLoadMoreKnowledgeDetail(knowledgeDetailBean: KnowledgeDetailBean) {
        list.addAll(knowledgeDetailBean.datas)
        adapter.notifyDataSetChanged()
        smartRefreshLayout.finishLoadMore()
    }

    override fun showRefreshKnowledgeDetail(knowledgeDetailBean: KnowledgeDetailBean) {
        list.clear()
        list.addAll(knowledgeDetailBean.datas)
        adapter.notifyDataSetChanged()
        smartRefreshLayout.finishRefresh()
    }

    override fun noMoreData() {
        smartRefreshLayout.finishLoadMoreWithNoMoreData()
    }

    override fun onFailed(refresh: Boolean) {
        if (refresh) {
            smartRefreshLayout.finishRefresh()
        } else {
            smartRefreshLayout.finishLoadMore()
        }
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