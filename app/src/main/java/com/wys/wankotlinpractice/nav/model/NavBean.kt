package com.wys.wankotlinpractice.nav.model

import com.wys.wankotlinpractice.home.mvvm.model.ArticleBean

data class NavBean(
    val articles: List<ArticleBean.Article>,
    val cid: Int,
    val name: String
)