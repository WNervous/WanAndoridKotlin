package com.wys.wankotlinpractice.knowledge.adapter

import android.support.annotation.LayoutRes
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.home.view.ArticleDetailActivity
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgePointBean

class KnowledgeItemAdapter(
    @LayoutRes int: Int,
    list: List<KnowledgePointBean>
) : BaseQuickAdapter<KnowledgePointBean, BaseViewHolder>(int, list) {
    override fun convert(helper: BaseViewHolder, item: KnowledgePointBean) {
        helper.getView<TextView>(R.id.title).text = item.title
        helper.getView<TextView>(R.id.authorName).text = item.author
        helper.getView<TextView>(R.id.collectionCount).text = item.zan.toString()
        helper.itemView.setOnClickListener {
            ArticleDetailActivity.open(mContext, item.title, item.link)
        }
    }
}