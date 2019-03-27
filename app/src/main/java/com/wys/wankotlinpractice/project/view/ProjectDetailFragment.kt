package com.wys.wankotlinpractice.project.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.view.adapter.ArticleAdapter
import com.wys.wankotlinpractice.project.contract.ProjectDetailContract
import com.wys.wankotlinpractice.project.presenter.ProjectDetailPresenter
import kotlinx.android.synthetic.main.fragment_project_detail.*

class ProjectDetailFragment : BaseFragment(), ProjectDetailContract.View {

    private var mlist = mutableListOf<ArticleBean.Article>()
    private lateinit var presenter: ProjectDetailPresenter
    private lateinit var mAdapter: ArticleAdapter
    private var cid: Int = 0

    override fun getContentViewId(): Int = R.layout.fragment_project_detail

    override fun init(savedInstanceState: Bundle?) {
        cid = arguments?.getInt(KEY_CID)!!
        presenter = ProjectDetailPresenter(this)
        recycleView.layoutManager = LinearLayoutManager(context)
        mAdapter = ArticleAdapter(R.layout.item_article, mlist)
        recycleView.adapter = mAdapter
        presenter.getDetailProjectItem(cid, true)
        smartRefreshLayout.setRefreshHeader(MaterialHeader(context))
        smartRefreshLayout.setRefreshFooter(ClassicsFooter(context))
        smartRefreshLayout.setOnRefreshListener {
            presenter.getDetailProjectItem(cid, true)
        }
        smartRefreshLayout.setOnLoadMoreListener {
            presenter.getDetailProjectItem(cid, false)
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    // 我是分割线
    //////////////////////////////////////////////////////////////////////////////

    override fun showDetailProjectItem(list: List<ArticleBean.Article>, refresh: Boolean) {
        if (refresh) {
            mlist.clear()
            mlist.addAll(list)
            smartRefreshLayout.finishRefresh()
        } else {
            mlist.addAll(list)
            smartRefreshLayout.finishLoadMore()
        }
        mAdapter.notifyDataSetChanged()
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

    override fun isActive(): Boolean = isAdded


    companion object {
        const val KEY_CID = "KEY_CID"
        fun newInstance(cid: Int): ProjectDetailFragment {
            val fragment = ProjectDetailFragment()
            val bundle = Bundle()
            bundle.putInt(KEY_CID, cid)
            fragment.arguments = bundle
            return fragment
        }
    }


}