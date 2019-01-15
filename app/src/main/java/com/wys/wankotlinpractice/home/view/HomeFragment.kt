package com.wys.wankotlinpractice.home.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.FrameLayout
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
        val view = View.inflate(context, R.layout.view_header_banner, null)
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

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = articleAdapter

//        swipeRefreshLayout.setOnRefreshListener {
//            OnRefreshListener {
//                homePresenter.getBanner()
//                homePresenter.getArticle()
//                it.finishRefresh(2000)
//            }
//        }

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
        articleAdapter.setNewData(articleBean.datas)
    }

    override fun isActive(): Boolean {
        return isAdded
    }


}