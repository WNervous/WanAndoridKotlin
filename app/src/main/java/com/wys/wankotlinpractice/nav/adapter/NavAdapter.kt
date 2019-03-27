package com.wys.wankotlinpractice.nav.adapter

import android.support.annotation.LayoutRes
import android.support.design.internal.FlowLayout
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.home.view.ArticleDetailActivity
import com.wys.wankotlinpractice.nav.model.NavBean

class NavAdapter(@LayoutRes int: Int, list: List<NavBean>) :
    BaseQuickAdapter<NavBean, BaseViewHolder>(int, list) {
    override fun convert(helper: BaseViewHolder, item: NavBean) {
        helper.getView<TextView>(R.id.category).text = item.name
        val flowLayout = helper.getView<FlowLayout>(R.id.flowLayout)
        for (article in item.articles) {
            val title = article.title
            val textView = TextView(mContext)
            textView.textSize = 16f
            textView.setPadding(0, 14, 30, 0)
            val content = SpannableString(title)
            content.setSpan(UnderlineSpan(), 0, title.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
            textView.text = content
            flowLayout.addView(textView)
            textView.setOnClickListener {
                ArticleDetailActivity.open(mContext,title,article.link)
            }
        }
    }
}