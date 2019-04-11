package com.wys.wankotlinpractice.login.manager

import android.text.TextUtils

object UserManager {
    lateinit var user: User
    fun login(): Boolean = TextUtils.isEmpty(user.id.toString())

    fun logOut(){

    }
}