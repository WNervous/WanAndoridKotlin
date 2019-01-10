package com.wys.wankotlinpractice.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseApi(builder: Builder) {
    private var mBaseUrl: String? = "http://www.wanandroid.com/"
    private var retrofit: Retrofit
    private var okHttpClient: OkHttpClient

    init {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpBuilder.retryOnConnectionFailure(true)
            .connectTimeout(builder.connectionTimeOut, TimeUnit.SECONDS)
            .readTimeout(builder.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(builder.writeTimeOut, TimeUnit.SECONDS)

        if (builder.headerInterceptor != null) okHttpBuilder.addInterceptor(builder.headerInterceptor!!)
        if (!builder.isRelease) {
            if (builder.httpLogInterceptor != null) okHttpBuilder.addInterceptor(builder.httpLogInterceptor!!)
            if (builder.networkInterceptor != null) okHttpBuilder.addNetworkInterceptor(builder.networkInterceptor!!)
        }
        okHttpClient = okHttpBuilder.build()
        mBaseUrl = if (builder.isRelease) builder.baseReleaseUrl!! else builder.baseDebugUrl!!
        retrofit = Retrofit.Builder()
            .baseUrl(mBaseUrl!!)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    class Builder {
        companion object {
            const val DEFAULT_TIME_OUT = 15
        }

        internal var networkInterceptor: Interceptor? = null
        internal var httpLogInterceptor: Interceptor? = null
        internal var headerInterceptor: Interceptor? = null

        internal var baseReleaseUrl: String? = null
        internal var baseDebugUrl: String? = null

        internal var readTimeOut = DEFAULT_TIME_OUT.toLong()
        internal var writeTimeOut = DEFAULT_TIME_OUT.toLong()
        internal var connectionTimeOut = DEFAULT_TIME_OUT.toLong()

        internal var isRelease = false

        fun builder(): BaseApi {
            return BaseApi(this)
        }
    }


    fun <T> createApi(t: Class<T>): T {
        return retrofit.create(t)
    }


}