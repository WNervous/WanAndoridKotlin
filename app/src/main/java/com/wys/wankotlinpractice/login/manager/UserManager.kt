package com.wys.wankotlinpractice.login.manager

import android.text.TextUtils
import com.tencent.mmkv.MMKV
import com.wys.wankotlinpractice.base.Constants.KEY_COOKIE
import com.wys.wankotlinpractice.base.Constants.KEY_USER_DATA
import com.wys.wankotlinpractice.net.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object UserManager {

    var user: User
    fun isLogin(): Boolean = TextUtils.isEmpty(user.id.toString())

    init {
        user = MMKV.defaultMMKV().decodeParcelable(KEY_USER_DATA, User::class.java)
    }

    fun logOut() {
        MMKV.defaultMMKV().encode(KEY_USER_DATA, "")
        MMKV.defaultMMKV().encode(KEY_COOKIE, "")
    }

    fun saveUserData(user: User) {
        this.user = user
        MMKV.defaultMMKV().encode(KEY_USER_DATA, user)
    }

    fun saveCookie(string: String) {
        MMKV.defaultMMKV().encode(KEY_COOKIE, string)
    }

    fun getCookie(cookiekey: String): String? {
        return MMKV.defaultMMKV().decodeString(KEY_COOKIE)
    }

    fun collectionAritcle(articleId: Int) {
        ApiService.sApi.wanApi().collectionArticle(articleId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun unCollection(articleId: Int) {
        ApiService.sApi.wanApi().cancelCollection(articleId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}