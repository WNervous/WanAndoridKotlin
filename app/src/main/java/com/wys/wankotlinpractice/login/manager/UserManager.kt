package com.wys.wankotlinpractice.login.manager

import android.content.Context
import android.text.TextUtils
import com.wys.wankotlinpractice.base.App

object UserManager {
    lateinit var user: User
    fun login(): Boolean = TextUtils.isEmpty(user.id.toString())

    fun logOut() {

    }

    fun saveCookie(string: String) {
        val sharedPreferences = App.content.getSharedPreferences("user", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("Cookie", string).apply()
    }

    fun getCookie(cookiekey: String): String {
        val sharedPreferences = App.content.getSharedPreferences("user", Context.MODE_PRIVATE)
        return sharedPreferences.getString("Cookie", "")
    }
}