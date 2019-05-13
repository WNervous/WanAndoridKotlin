package com.wys.wankotlinpractice.net.interceptor

import com.wys.wankotlinpractice.login.manager.UserManager
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {

    private val KEY_STRING = "/user/login"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().toString()
        val resp = chain.proceed(request)
        if (url.contains(KEY_STRING)) {
            val cookies = resp.headers("Set-Cookie")
            UserManager.saveCookie(encodeCookie(cookies))
//            KLog.d(cookies)
//            cookieStr = ""
//            if (TextUtils.isEmpty(cookieStr) && cookies.size > 0) {
//                for (i in cookies.indices) {
//                    cookieStr += cookies[i]
//                }
//                if (!TextUtils.isEmpty(cookieStr)) {
//                    Log.d("COOKIE", cookieStr)
//                    UserManager.saveCookie(cookieStr)
//                }
//            }
        }
        return resp
    }


    private fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies
            .map { cookie ->
                cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            }
            .forEach {
                it.filterNot { set.contains(it) }.forEach { set.add(it) }
            }

        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie).append(";")
        }

        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }

        return sb.toString()
    }
}