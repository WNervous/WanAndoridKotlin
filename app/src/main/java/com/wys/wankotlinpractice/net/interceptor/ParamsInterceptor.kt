package com.wys.wankotlinpractice.net.interceptor

import okhttp3.Request


class ParamsInterceptor : BaseInterceptor() {
    override fun config(request: Request): Request {
        val httpUrl = request.url().newBuilder()
            .addQueryParameter("platform", "android")
            .addQueryParameter("version", "1.0.0")
            .build()
        return request.newBuilder().url(httpUrl).build()
    }
}