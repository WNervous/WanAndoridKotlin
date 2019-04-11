package com.wys.wankotlinpractice.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class HeaderInterceptor : Interceptor {

    private val PARAMS_KEY_API_VERSION = "apiVersion"
    private val PARAMS_KEY_API_TIMEZONE = "timezone"
    private val PARAMS_KEY_PLATFORM = "platform"
    private val PARAMS_LOCAL_COOKIE = "cookie"
    private val API_VERSION = "1"
    private val PLATFORM = "android"
    private var sPackageName: String? = null
    private var sVersionName: String? = null
    private var mCookie: String = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        val builder = request.newBuilder()
        builder
            .addHeader(PARAMS_KEY_API_VERSION, API_VERSION)
            .addHeader(PARAMS_KEY_PLATFORM, PLATFORM)
            .addHeader(PARAMS_KEY_API_TIMEZONE, TimeZone.getDefault().id)
            .addHeader("User-Agent", "Android/$sPackageName/$sVersionName")
            .build()

        val proceed = chain.proceed(request)
//        val header = proceed.headers("Set-Cookie")
//        val empty = header.isEmpty()
//        if (!empty) {
//            val cookieBuffer = StringBuilder()
//            Observable.from(header)
//                .map({ s ->
//                    val cookieArray = s.split(";")
//                    cookieArray[0]
//                })
//                .subscribe({ cookie ->
//                    val append = cookieBuffer.append(cookie).append(";")
//                    if (append != null) {
//                        Preferences.(PARAMS_LOCAL_COOKIE, append.toString())
//                        mCookie = append.toString()
//                    }
//                })
//        }
        return proceed
    }

//    private fun getCookie(): String {
//        mCookie = Preferences.getString(PARAMS_LOCAL_COOKIE)
//        return mCookie
//    }

}