package com.wys.wankotlinpractice.utils

import android.content.Context
import android.util.DisplayMetrics


class ScreenUitl {

    companion object {
        fun getScreenInfo(context: Context?): DisplayMetrics {
            val resources = context!!.resources
            return resources.displayMetrics
        }
    }
}