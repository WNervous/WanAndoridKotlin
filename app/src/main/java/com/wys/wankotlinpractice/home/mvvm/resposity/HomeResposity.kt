package com.wys.wankotlinpractice.home.mvvm.resposity

import androidx.lifecycle.MutableLiveData
import com.wys.wankotlinpractice.home.mvvm.model.ArticleBean
import com.wys.wankotlinpractice.net.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeResposity {

    var mutableList = MutableLiveData<List<ArticleBean.Article>>()

    fun remoteData(page: Int) {
        val subscribe = ApiService.sApi.wanApi().getHomeArticle(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mutableList.value = it.data.datas
            }
    }
}