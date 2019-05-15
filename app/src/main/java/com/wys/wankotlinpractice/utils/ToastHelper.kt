package com.wys.wankotlinpractice.utils

import android.widget.Toast
import androidx.annotation.StringRes
import com.wys.wankotlinpractice.base.App

object ToastHelper {

    fun showShort(msg: CharSequence) {
        Toast.makeText(App.content, msg, Toast.LENGTH_SHORT).show()
    }

    fun showShort(@StringRes msg: Int) {
        Toast.makeText(App.content, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLong(msg: CharSequence) {
        Toast.makeText(App.content, msg, Toast.LENGTH_LONG).show()
    }

    fun showLong(@StringRes msg: Int) {
        Toast.makeText(App.content, msg, Toast.LENGTH_LONG).show()
    }
}
