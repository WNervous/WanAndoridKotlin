package com.wys.wankotlinpractice.utils

import com.google.gson.Gson

object GsonUtils {

    val gson = Gson()

    fun toString(any: Any): String {
        return gson.toJson(any)
    }

    fun toAny(string: String?): Any {
        return gson.fromJson(string, Any::class.java)
    }
}