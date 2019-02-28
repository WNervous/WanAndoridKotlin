package com.wys.wankotlinpractice.net

import com.wys.wankotlinpractice.net.interceptor.HeaderInterceptor
import okhttp3.logging.HttpLoggingInterceptor

class ApiService {
    private val DEFAULT_TIME_OUT = 15.toLong()

    private val mBaseUrl = "http://www.wanandroid.com/"
    private val mBaseTestUrl = "http://www.wanandroid.com/"

    private lateinit var mBaseApi: BaseApi

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        with(BaseApi.Builder()) {
            baseReleaseUrl = mBaseUrl
            baseDebugUrl = mBaseTestUrl
            connectionTimeOut = DEFAULT_TIME_OUT
            readTimeOut = DEFAULT_TIME_OUT
            writeTimeOut = DEFAULT_TIME_OUT
            headerInterceptor = HeaderInterceptor()
            httpLogInterceptor = httpLoggingInterceptor
            mBaseApi = builder()
        }
    }

    companion object {
        val sApi: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiService()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Method To Get API
    ///////////////////////////////////////////////////////////////////////////

    fun wanApi(): WanApi {
        return mBaseApi.createApi(WanApi::class.java)
    }
}