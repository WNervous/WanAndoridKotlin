package com.wys.wankotlinpractice.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayoutId())
        init(savedInstanceState)
    }

    abstract fun contentLayoutId(): Int
    abstract fun init(savedInstanceState: Bundle?)
}