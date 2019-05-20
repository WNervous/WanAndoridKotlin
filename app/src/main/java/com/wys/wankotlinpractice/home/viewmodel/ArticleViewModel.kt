package com.wys.wankotlinpractice.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    private var articleList = mutableListOf<ArticleBean.Article>()

    var mulLiveData = MutableLiveData<MutableList<ArticleBean.Article>>()

    fun getLiveData(): MutableLiveData<MutableList<ArticleBean.Article>> {
        return mulLiveData
    }

    fun setData(data: MutableList<ArticleBean.Article>) {
        articleList = data
        mulLiveData.value = articleList
    }

    fun addData(data: MutableList<ArticleBean.Article>) {
        articleList.addAll(data)
        mulLiveData.value = articleList
    }
}