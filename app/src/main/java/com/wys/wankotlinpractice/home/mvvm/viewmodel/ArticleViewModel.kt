package com.wys.wankotlinpractice.home.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wys.wankotlinpractice.home.mvvm.model.ArticleBean

class ArticleViewModel : ViewModel() {

    private var articleList = mutableListOf<ArticleBean.Article>()

    var mulLiveData = MutableLiveData<MutableList<ArticleBean.Article>>()

    fun setData(data: MutableList<ArticleBean.Article>) {
        articleList = data
        mulLiveData.value = articleList
    }

    fun addData(data: MutableList<ArticleBean.Article>) {
        articleList.addAll(data)
        mulLiveData.value = articleList
    }


}