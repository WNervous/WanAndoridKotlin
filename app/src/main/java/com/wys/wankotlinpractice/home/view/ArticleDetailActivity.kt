package com.wys.wankotlinpractice.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseActivity
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : BaseActivity() {

    override fun contentLayoutId(): Int = R.layout.activity_article_detail

    override fun init(savedInstanceState: Bundle?) {

        val link = intent.getStringExtra(KEY_LINK)
        val title = intent.getStringExtra(KEY_TITLE)

        toolbar.title = title
        webView.loadUrl(link)
        webView.webChromeClient = WebClient(refreshLayout)
        webView.webViewClient = MyWebClient()
        toolbar.setNavigationOnClickListener { finish() }
        refreshLayout.setOnRefreshListener {
            webView.reload()
        }

    }

    //////////////////////////////////////////////////////////////////////////////
    //inner class
    //////////////////////////////////////////////////////////////////////////////
    class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return super.shouldOverrideUrlLoading(view, request)
        }
    }


    class WebClient(var refreshLayout: SmartRefreshLayout) : WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) {
                refreshLayout.finishRefresh()
            }
        }

    }


    companion object {
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_LINK = "KEY_LINK"

        fun open(context: Context, title: String, link: String) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(KEY_TITLE, title)
            intent.putExtra(KEY_LINK, link)
            context.startActivity(intent)
        }
    }

}