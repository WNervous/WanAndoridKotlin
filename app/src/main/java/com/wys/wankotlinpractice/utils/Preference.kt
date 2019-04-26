package com.wys.wankotlinpractice.utils

import android.content.Context
import android.content.SharedPreferences
import com.wys.wankotlinpractice.base.App

object Preference {

    private val preferences: SharedPreferences = App.content.getSharedPreferences("wanandroid", Context.MODE_PRIVATE)

    fun setString(key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }


    private fun getString(key: String, default: String): String? {
        return preferences.getString(key, default)
    }

    fun getString(key: String): String? {
        return getString(key, "")
    }
}