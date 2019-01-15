package com.wys.wankotlinpractice.net

import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.mvp.model.Banner
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeBean
import com.wys.wankotlinpractice.net.bean.CommonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WanApi {

    /** home */

    // banner   :  http://www.wanandroid.com/banner/json
    @GET("banner/json")
    fun getHomeBanner(): Observable<CommonResponse<List<Banner>>>

    @GET("article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<CommonResponse<ArticleBean>>


    /** knowledge*/
    @GET("tree/json")
    fun getKnowledgeService(): Observable<CommonResponse<List<KnowledgeBean>>>

    @GET("article/list/{page}/json?cid={cid}")
    fun getSeriesArticle(@Path("page") page: Int, @Path("cid") cid: Int)


}