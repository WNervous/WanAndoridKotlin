package com.wys.wankotlinpractice.net.interceptor

import android.util.Log
import com.wys.wankotlinpractice.base.App
import com.wys.wankotlinpractice.login.manager.UserManager
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class HeaderInterceptor : Interceptor {

    private val PARAMS_KEY_API_VERSION = "apiVersion"
    private val PARAMS_KEY_API_TIMEZONE = "timezone"
    private val PARAMS_KEY_PLATFORM = "platform"
    private val PARAMS_LOCAL_COOKIE = "Cookie"
    private val API_VERSION = "1"
    private val PLATFORM = "android"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder
            .addHeader(PARAMS_KEY_API_VERSION, API_VERSION)
            .addHeader(PARAMS_KEY_PLATFORM, PLATFORM)
            .addHeader(PARAMS_KEY_API_TIMEZONE, TimeZone.getDefault().id)
            .addHeader("User-Agent", "Android/${App.getAppPackageName()}/${App.getVersionName()}")

        val cookie = UserManager.getCookie()
        if (cookie != null) {
            Log.d("HeaderInterceptor", cookie)
            builder.addHeader(PARAMS_LOCAL_COOKIE, cookie)
        }

        return chain.proceed(builder.build())
    }
}