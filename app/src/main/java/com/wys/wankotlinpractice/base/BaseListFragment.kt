package com.wys.wankotlinpractice.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.wys.wankotlinpractice.R

abstract class BaseListFragment<T : Any> : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var smartRefreshLayout: SmartRefreshLayout

    lateinit var adapter: BaseQuickAdapter<T, BaseViewHolder>

    var isLoading: Boolean = false
    private var hasMore: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentViewId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView(view)
        initSmartRefreshView(view)
        init(savedInstanceState)
    }

    private fun initRecycleView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = createLayoutManager()
        recyclerView.layoutManager = layoutManager
        adapter = createAdapter()
        recyclerView.adapter = adapter
    }

    private fun initSmartRefreshView(view: View) {
        smartRefreshLayout = view.findViewById(R.id.refreshLayout)
        smartRefreshLayout.setOnRefreshListener { refreshData() }
        smartRefreshLayout.setOnLoadMoreListener {
            requestMoreData()
        }
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false)
        //设置默认的加载 loading header 和 footer
        smartRefreshLayout.setRefreshHeader(MaterialHeader(context!!))
        val classicsFooter = ClassicsFooter(context!!)
        smartRefreshLayout.setRefreshFooter(classicsFooter)
    }

    abstract fun createAdapter(): BaseQuickAdapter<T, BaseViewHolder>

    private fun createLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }

////////////////////////////////////////////////////////////////
/// inner method
////////////////////////////////////////////////////////////////

    private fun hasMore(): Boolean {
        return hasMore
    }

    fun setHasMore(hasMore: Boolean) {
        this.hasMore = hasMore
    }

    /**
     * 设置  smartrefreshlayoutt  header footer
     */
    fun setRefreshHeader(refreshHeader: RefreshHeader) {
        smartRefreshLayout.setRefreshHeader(refreshHeader)
    }

    fun setRefreshFooter(refreshFooter: RefreshFooter) {
        smartRefreshLayout.setRefreshFooter(refreshFooter)
    }

    /**
     * 设置  recycleView  header footer
     */
    fun addRecycleViewHeader(header: View) {
        addRecycleViewHeader(header, -1)
    }

    private fun addRecycleViewHeader(header: View, index: Int) {
        adapter.addHeaderView(header, index)
    }

    fun addRecycleViewFooter(view: View) {
        addRecycleViewFooter(view, -1)
    }

    private fun addRecycleViewFooter(view: View, index: Int) {
        adapter.addFooterView(view, index)
    }

    /**
     * 禁止下拉刷新
     */
    fun enableRefresh(refresh: Boolean) {
        smartRefreshLayout.setEnableRefresh(refresh)
    }

    /**
     * 禁止上拉加载
     */
    fun enableLoadMore(loadMore: Boolean) {
        smartRefreshLayout.setEnableLoadMore(loadMore)
    }

    fun complete() {
        isLoading = false
    }

    /**
     * 下拉刷新完成
     *
     * @param success 是否成功
     */
    fun refreshSuccess(success: Boolean) {
        complete()
        if (success) {
            smartRefreshLayout.finishRefresh()
        } else {
            smartRefreshLayout.finishRefresh(false)//表示刷新失败（不会更新时间）
        }
    }

    /**
     * 上拉加载更多完成
     *
     * @param success 是否成功
     */
    fun loadSuccess(success: Boolean) {
        complete()
        if (success) {
            if (hasMore()) {
                smartRefreshLayout.finishLoadMore()
            } else {
                smartRefreshLayout.finishLoadMoreWithNoMoreData()//复原状态
            }
        } else {
            smartRefreshLayout.finishLoadMore(false)//表示刷新失败（不会更新时间）
        }
    }

    /**
     * 下拉刷新数据
     */
    private fun refreshData() {
        if (isLoading) {
            return
        }
        isLoading = true
        refresh()
    }

    /**
     * 上拉加载更多数据
     */
    private fun requestMoreData() {
        if (isLoading) {
            return
        }
        isLoading = true
        loadMore()
    }

    abstract fun refresh()

    abstract fun loadMore()

    abstract val contentViewId: Int

    protected abstract fun init(bundle: Bundle?)

}


