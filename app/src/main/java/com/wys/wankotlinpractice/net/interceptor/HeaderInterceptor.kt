package com.wys.wankotlinpractice.net.interceptor

import okhttp3.Request
import java.util.*

class HeaderInterceptor : BaseInterceptor() {

    private val PARAMS_KEY_API_VERSION = "apiVersion"
    private val PARAMS_KEY_API_TIMEZONE = "timezone"
    private val PARAMS_KEY_PLATFORM = "platform"
    private val API_VERSION = "1"
    private val PLATFORM = "android"
    private var sPackageName: String? = null
    private var sVersionName: String? = null

    override fun config(request: Request): Request {
        val builder = request.newBuilder()
        return builder
            .addHeader(PARAMS_KEY_API_VERSION, API_VERSION)
            .addHeader(PARAMS_KEY_PLATFORM, PLATFORM)
            .addHeader(PARAMS_KEY_API_TIMEZONE, TimeZone.getDefault().id)
            .addHeader("User-Agent", "Android/$sPackageName/$sVersionName")
            .build()

    }

}