package com.wys.wankotlinpractice.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.wys.wankotlinpractice.R

abstract class BaseListFragment<T : Any> : Fragment() {

    private lateinit var recyclerView: RecyclerView

    var smartRefreshLayout: SmartRefreshLayout? = null

    var adapter: BaseQuickAdapter<T, BaseViewHolder>? = null

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
        init(savedInstanceState)
        initRecycleView(view)
        initSmartRefreshView(view)
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
        smartRefreshLayout!!.setOnRefreshListener { refreshData() }
        smartRefreshLayout!!.setOnLoadMoreListener {
            if (adapter != null) {
                requestMoreData()
            }
        }
        smartRefreshLayout!!.setEnableLoadMoreWhenContentNotFull(false)
        //设置默认的加载 loading header 和 footer
        smartRefreshLayout!!.setRefreshHeader(MaterialHeader(context!!))
        val classicsFooter = ClassicsFooter(context!!)
        smartRefreshLayout!!.setRefreshFooter(classicsFooter)
    }

    abstract fun createAdapter(): BaseQuickAdapter<T, BaseViewHolder>

    private fun createLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }

////////////////////////////////////////////////////////////////
/// inner method
////////////////////////////////////////////////////////////////

    fun hasMore(): Boolean {
        return hasMore
    }

    fun setHasMore(hasMore: Boolean) {
        this.hasMore = hasMore
    }

    @JvmOverloads
    fun addHeaderView(header: View, index: Int = -1) {
        adapter!!.addHeaderView(header, index)
    }

    @JvmOverloads
    fun addFooterView(view: View, index: Int = -1) {
        adapter!!.addFooterView(view, index)
    }

    /**
     * 禁止下拉刷新
     */
    fun enableRefresh(refresh: Boolean) {
        smartRefreshLayout!!.setEnableRefresh(refresh)
    }

    /**
     * 禁止上拉加载
     */
    fun enableloadMore(loadMore: Boolean) {
        smartRefreshLayout!!.setEnableLoadMore(loadMore)
    }

    fun complete() {
        if (smartRefreshLayout == null) {
            return
        }
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
            smartRefreshLayout!!.finishRefresh()
            smartRefreshLayout!!.resetNoMoreData()//复原状态
        } else {
            smartRefreshLayout!!.finishRefresh(false)//表示刷新失败（不会更新时间）
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
                smartRefreshLayout!!.finishRefresh()
            } else {
                smartRefreshLayout!!.finishLoadMoreWithNoMoreData()//复原状态
            }
        } else {
            smartRefreshLayout!!.finishRefresh(false)//表示刷新失败（不会更新时间）
        }
    }

    /**
     * 下拉刷新数据
     */
    protected fun refreshData() {
        if (isLoading) {
            return
        }
        isLoading = true
        refresh()
    }

    /**
     * 上拉加载更多数据
     */
    fun requestMoreData() {
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


