package com.wys.wankotlinpractice.home.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.glide.GlideImageLoader
import com.wys.wankotlinpractice.home.mvp.contract.HomeContract
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.mvp.model.Banner
import com.wys.wankotlinpractice.home.mvp.presenter.HomePresenter
import com.wys.wankotlinpractice.home.view.adapter.ArticleAdapter
import com.wys.wankotlinpractice.utils.ScreenUitl
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_header_banner.view.*


class HomeFragment : BaseFragment(), HomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private val articleAdapter: ArticleAdapter = ArticleAdapter(R.layout.item_article, null)
    private lateinit var bannerView: com.youth.banner.Banner
    override fun getContentViewId(): Int {
        return R.layout.fragment_home
    }

    override fun init(savedInstanceState: Bundle?) {
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = articleAdapter
        val view = LayoutInflater.from(context).inflate(R.layout.view_header_banner, recycleView, false)
        bannerView = view.bannerView
        val layoutParams = bannerView.layoutParams as FrameLayout.LayoutParams
        layoutParams.width = ScreenUitl.getScreenInfo(context).widthPixels
        layoutParams.height = ScreenUitl.getScreenInfo(context).widthPixels / 9 * 5
        bannerView.layoutParams = layoutParams
        bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        bannerView.isAutoPlay(true)
        bannerView.setIndicatorGravity(BannerConfig.CENTER)
        bannerView.setDelayTime(2500)
        bannerView.setImageLoader(GlideImageLoader())

        if (articleAdapter.headerLayout == null) {
            articleAdapter.setHeaderView(view)
        }

        refreshLayout.setOnRefreshListener {
            homePresenter.getArticle()
        }

        refreshLayout.setOnLoadMoreListener {
            homePresenter.loadMoreArticle()
        }

        homePresenter = HomePresenter(this)
        homePresenter.getBanner()
        homePresenter.getArticle()
    }

    override fun onStart() {
        super.onStart()
        bannerView.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        bannerView.stopAutoPlay()
    }

    override fun showBanners(banner: List<Banner>) {
        val images: MutableList<String> = mutableListOf()
        for (url in banner) {
            images.add(url.imagePath)
        }
        bannerView.setImages(images)
        bannerView.setOnBannerListener {
            val banner = banner[it]
            ArticleDetailActivity.open(context!!, banner.title, banner.url)
        }
        bannerView.start()
    }

    override fun showArticles(articleBean: ArticleBean) {
        if (articleBean.curPage == 1) {
            articleAdapter.setNewData(articleBean.datas)
            refreshLayout.finishRefresh()
        } else {
            articleAdapter.addData(articleBean.datas)
            refreshLayout.finishLoadMore()
        }
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun articleError() {
        Toast.makeText(context, "article error", Toast.LENGTH_SHORT).show()
        refreshLayout.finishLoadMore()
    }

    override fun bannerError() {
        refreshLayout.finishLoadMore()
        Toast.makeText(context, "banner error", Toast.LENGTH_SHORT).show()
    }

    override fun noMoreArticle(): Boolean {
        refreshLayout.finishLoadMore()
        return true
    }


}