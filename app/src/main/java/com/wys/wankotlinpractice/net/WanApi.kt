package com.wys.wankotlinpractice.net

import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.mvp.model.Banner
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeBean
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean
import com.wys.wankotlinpractice.nav.model.NavBean
import com.wys.wankotlinpractice.net.bean.CommonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanApi {

    /** home */

    // banner   :  http://www.wanandroid.com/banner/json
    @GET("banner/json")
    fun getHomeBanner(): Observable<CommonResponse<List<Banner>>>

    @GET("article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<CommonResponse<ArticleBean>>


    /** knowledge*/
    @GET("tree/json")
    fun getKnowledgeSeries(): Observable<CommonResponse<List<KnowledgeBean>>>

    @GET("article/list/{page}/json")
    fun getSeriesDetails(@Path("page") page: Int, @Query("cid") cid: Int): Observable<CommonResponse<KnowledgeDetailBean>>

    /**nav */
    @GET("navi/json")
    fun getNav() :Observable<CommonResponse<List<NavBean>>>

}