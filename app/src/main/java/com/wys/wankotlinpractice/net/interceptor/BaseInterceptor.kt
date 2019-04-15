package com.wys.wankotlinpractice.net.interceptor

import android.text.TextUtils
import com.wys.wankotlinpractice.login.manager.UserManager
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val resp = chain.proceed(chain.request())
        val cookies = resp.headers("Set-Cookie")
        var cookieStr = ""
        if (TextUtils.isEmpty(cookieStr) && cookies.size > 0) {
            for (i in cookies.indices) {
                cookieStr += cookies[i]
            }
            UserManager.saveCookie(cookieStr)
        }
        return resp
    }
}