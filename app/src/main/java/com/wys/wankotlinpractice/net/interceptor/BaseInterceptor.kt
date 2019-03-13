package com.wys.wankotlinpractice.net.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

abstract class BaseInterceptor : Interceptor {

    override fun intercept(chian: Interceptor.Chain): Response {
        return chian.proceed(config(chian.request()))
    }

    abstract fun config(request: Request): Request
}