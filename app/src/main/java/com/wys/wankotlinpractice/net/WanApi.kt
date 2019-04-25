package com.wys.wankotlinpractice.net

import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.home.mvp.model.Banner
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeBean
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeDetailBean
import com.wys.wankotlinpractice.login.manager.User
import com.wys.wankotlinpractice.login.model.LoginBean
import com.wys.wankotlinpractice.nav.model.NavBean
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.project.model.ProjectBean
import io.reactivex.Observable
import retrofit2.http.*

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
    fun getNav(): Observable<CommonResponse<List<NavBean>>>

    /**project*/
    @GET("project/tree/json")
    fun getProjects(): Observable<CommonResponse<MutableList<ProjectBean>>>

    @GET("project/list/{page}/json")
    fun getProjectDetails(@Path("page") page: Int, @Query("cid") cid: Int): Observable<CommonResponse<ArticleBean>>

    /**用户相关*/  //注册
    @FormUrlEncoded
    @POST("/user/register")
    fun register2(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String): Observable<CommonResponse<User>>

    //登陆
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Body login: LoginBean): Observable<CommonResponse<User>>

    //登陆
    @FormUrlEncoded
    @POST("/user/login")
    fun login2(@Field("username") username: String, @Field("password") password: String): Observable<CommonResponse<User>>

    //登出
    @GET("/user/logout/json")
    fun loginout(): Observable<CommonResponse<Any>>

    // 收藏
    @POST("lg/collect/{id}/json")
    fun collectionArticle(@Path("id") id: Int): Observable<CommonResponse<Any>>

    //取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    fun cancelCollection(@Path("id") id: Int): Observable<CommonResponse<Any>>

}