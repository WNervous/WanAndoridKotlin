package com.wys.wankotlinpractice.home.mvvm.model

import java.io.Serializable

data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
) : Serializable