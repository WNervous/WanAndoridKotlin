package com.wys.wankotlinpractice.knowledge.adapter

import android.support.annotation.LayoutRes
import android.support.design.internal.FlowLayout
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeBean

class KnowledgeAdapter(@LayoutRes int: Int, list: List<KnowledgeBean>) :
    BaseQuickAdapter<KnowledgeBean, BaseViewHolder>(int, list) {

    override fun convert(helper: BaseViewHolder, item: KnowledgeBean) {
        helper.getView<TextView>(R.id.knowledgeType).text = item.name
        val flowLayout = helper.getView<FlowLayout>(R.id.knowledgeLayout)
        for (childrenTree in item.children) {
            val name = childrenTree.name
            val textView = TextView(helper.itemView.context)
            textView.setPadding(0, 8, 12, 0)
            val content = SpannableString(name)
            content.setSpan(UnderlineSpan(), 0, name.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
            textView.text = content
            textView.setOnClickListener { }
            flowLayout.addView(textView)
        }
    }

}