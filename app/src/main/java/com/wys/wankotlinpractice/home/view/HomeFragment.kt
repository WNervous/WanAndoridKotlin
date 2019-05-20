package com.wys.wankotlinpractice.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.socks.library.KLog
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseListFragment
import com.wys.wankotlinpractice.glide.GlideImageLoader
import com.wys.wankotlinpractice.home.mvp.contract.HomeContract
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.mvp.model.Banner
import com.wys.wankotlinpractice.home.mvp.presenter.HomePresenter
import com.wys.wankotlinpractice.home.view.adapter.ArticleAdapter
import com.wys.wankotlinpractice.home.viewmodel.ArticleViewModel
import com.wys.wankotlinpractice.utils.ScreenUtil
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_header_banner.view.*

class HomeFragment : BaseListFragment<ArticleBean.Article>(), HomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private lateinit var bannerView: com.youth.banner.Banner
    lateinit var articleViewModel: ArticleViewModel

    override fun createAdapter(): BaseQuickAdapter<ArticleBean.Article, BaseViewHolder> {
        return ArticleAdapter(R.layout.item_article, null)
    }

    override val contentViewId: Int = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        articleViewModel.mulLiveData.observe(this, Observer {
            adapter.setNewData(it)
            KLog.d(it)
        })
        val view = LayoutInflater.from(context).inflate(R.layout.view_header_banner, recyclerView, false)
        bannerView = view.bannerView
        val layoutParams = bannerView.layoutParams as FrameLayout.LayoutParams
        layoutParams.width = ScreenUtil.getScreenInfo(context).widthPixels
        layoutParams.height = ScreenUtil.getScreenInfo(context).widthPixels / 9 * 5
        bannerView.layoutParams = layoutParams
        bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        bannerView.isAutoPlay(true)
        bannerView.setIndicatorGravity(BannerConfig.CENTER)
        bannerView.setDelayTime(2500)
        bannerView.setImageLoader(GlideImageLoader())

        if (adapter.headerLayout == null) {
            adapter.setHeaderView(view)
        }

        homePresenter = HomePresenter(this)
        homePresenter.getBanner()
        homePresenter.getArticle(true)
    }

    override fun refresh() {
        homePresenter.getBanner()
        homePresenter.getArticle(true)
    }

    override fun loadMore() {
        homePresenter.getArticle(false)
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

    override fun showArticles(articleBean: ArticleBean, refresh: Boolean) {
        if (refresh) {
            articleViewModel.setData(articleBean.datas)
            refreshSuccess(true)
        } else {
            articleViewModel.addData(articleBean.datas)
            setHasMore(!articleBean.over)
            loadSuccess(true)
        }
    }

    override fun noMoreArticle() {
        setHasMore(false)
        loadSuccess(true)
    }

    override fun articleError() {

    }

    override fun bannerError() {
        Toast.makeText(context, "banner error", Toast.LENGTH_SHORT).show()
    }

    override fun isActive(): Boolean = isAdded


    override fun onStart() {
        super.onStart()
        bannerView.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        bannerView.stopAutoPlay()
    }
}