package com.wys.wankotlinpractice.home.view.weidget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.home.mvp.model.ArticleBean
import com.wys.wankotlinpractice.login.LoginActivity
import com.wys.wankotlinpractice.login.manager.UserManager
import kotlinx.android.synthetic.main.view_article.view.*

class ArticleView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        addView(LayoutInflater.from(getContext()).inflate(R.layout.view_article, this, false))
    }

    fun setArticleData(article: ArticleBean.Article) {
        articleTitle.text = article.title
        authorName.text = article.author
        desc.text = article.desc
//        publishTime.text=DateUtils.form(context,article.publishTime,"XXXX")
        superName.text = article.superChapterName
        if (TextUtils.isEmpty(article.envelopePic)) {
            articleImg.visibility = View.GONE
            desc.visibility = View.GONE
        } else {
            articleImg.visibility = View.VISIBLE
            desc.visibility = View.VISIBLE
            val options = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_data)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

            Glide.with(this).load(article.envelopePic).apply(options).into(articleImg)
        }
        collection.setImageResource(if (article.collect) R.drawable.ic_collection else R.drawable.ic_uncollection)
        collection.setOnClickListener {
            if (!UserManager.isLogin()) {
                LoginActivity.open(context)
            } else {
                if (article.collect) {
                    UserManager.unCollection(articleId = article.id)
                } else {
                    UserManager.collectionArticle(articleId = article.id)
                }
                collection.setImageResource(if (article.collect) R.drawable.ic_uncollection else R.drawable.ic_collection)
                article.collect = !article.collect
            }
        }
    }


}