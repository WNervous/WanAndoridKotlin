package com.wys.wankotlinpractice.knowledge.mvp.model

import java.io.Serializable

data class KnowledgeDetailBean(
    val datas: List<KnowledgePointBean>,
    val curPager: Int,
    val offset: Int,
    val over: Boolean,
    val pagerCount: Int,
    val size: Int,
    val total: Int
) : Serializable

data class KnowledgePointBean(
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val projectLink: String,
    val publishTime: Long,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Any>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) : Serializable


