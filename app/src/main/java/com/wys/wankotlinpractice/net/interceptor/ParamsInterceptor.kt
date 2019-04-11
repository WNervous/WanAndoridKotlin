package com.wys.wankotlinpractice.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response


class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url().newBuilder()
            .addQueryParameter("platform", "android")
            .addQueryParameter("version", "1.0.0")
            .build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}