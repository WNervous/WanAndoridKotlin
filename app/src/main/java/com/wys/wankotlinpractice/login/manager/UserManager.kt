package com.wys.wankotlinpractice.login.manager

import android.text.TextUtils
import com.wys.wankotlinpractice.base.Constants
import com.wys.wankotlinpractice.base.Constants.KEY_COOKIE
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.utils.GsonUtils
import com.wys.wankotlinpractice.utils.Preference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object UserManager {

    private var user: User? = null
    fun isLogin(): Boolean = user != null && !TextUtils.isEmpty(user?.id.toString())

//    init {
//        val string = Preference.getString(Constants.KEY_USER_DATA)
//        if (!TextUtils.isEmpty(string)) {
//            user = GsonUtils.toAny(string) as User
//        }
//    }

    fun logOut() {

    }

    fun saveUserData(user: User) {
        this.user = user
        Preference.setString(Constants.KEY_USER_DATA, GsonUtils.toString(user))
    }

    fun saveCookie(string: String?) {
        Preference.setString(Constants.KEY_COOKIE, string)
    }

    fun getCookie(): String? {
        return Preference.getString(KEY_COOKIE)
    }

    fun collectionArticle(articleId: Int) {
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