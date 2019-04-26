package com.wys.wankotlinpractice.net.interceptor

import android.text.TextUtils
import android.util.Log
import com.socks.library.KLog
import com.wys.wankotlinpractice.login.manager.UserManager
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    lateinit var cookieStr: String
    override fun intercept(chain: Interceptor.Chain): Response {
        val resp = chain.proceed(chain.request())
        val cookies = resp.headers("Set-Cookie")
        KLog.d(cookies)
        cookieStr = ""
        if (TextUtils.isEmpty(cookieStr) && cookies.size > 0) {
            for (i in cookies.indices) {
                cookieStr += cookies[i]
            }
            if (!TextUtils.isEmpty(cookieStr)) {
                Log.d("COOKIE", cookieStr)
                UserManager.saveCookie(cookieStr)
            }
        }
        return resp
    }
}