package com.wys.wankotlinpractice.home.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.view.ArticleDetailActivity
import com.wys.wankotlinpractice.home.view.weidget.ArticleView

class ArticleAdapter(layoutResId: Int, data: List<ArticleBean.Article>?) :
    BaseQuickAdapter<ArticleBean.Article, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: ArticleBean.Article) {
        val articleView = helper.getView<ArticleView>(R.id.articleView)
        articleView.setArticleData(item)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val article = data[holder.adapterPosition]
            ArticleDetailActivity.open(mContext, article.title, article.link)
        }
    }
}

