package com.wys.wankotlinpractice.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayoutId())
        init(savedInstanceState)
    }

    abstract fun contentLayoutId(): Int
    abstract fun init(savedInstanceState: Bundle?)
}