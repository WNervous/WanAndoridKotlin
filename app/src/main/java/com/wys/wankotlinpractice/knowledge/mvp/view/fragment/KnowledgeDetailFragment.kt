package com.wys.wankotlinpractice.knowledge.mvp.view.fragment

import android.os.Bundle
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import com.wys.wankotlinpractice.knowledge.adapter.KnowledgePagerAdapter
import com.wys.wankotlinpractice.knowledge.mvp.model.KnowledgeBean
import com.wys.wankotlinpractice.knowledge.mvp.view.activity.KnowledgeDetailActivity
import kotlinx.android.synthetic.main.fragment_knowledge_detail.*

class KnowledgeDetailFragment : BaseFragment() {

    override fun getContentViewId(): Int = R.layout.fragment_knowledge_detail

    override fun init(savedInstanceState: Bundle?) {
        toolbar.setNavigationOnClickListener { activity?.finish() }
        val knowledgeBean = arguments?.getSerializable(KnowledgeDetailActivity.KEY_KNOWLEDGE) as KnowledgeBean
        toolbar.title = knowledgeBean.name

        viewPager.adapter = KnowledgePagerAdapter(requireFragmentManager(), knowledgeBean.children)

        slidingTabLayout.setupWithViewPager(viewPager, false)
    }

    companion object {
        fun newInstance(knowledgeBean: KnowledgeBean): KnowledgeDetailFragment {
            val knowledgeDetailFragment = KnowledgeDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(KnowledgeDetailActivity.KEY_KNOWLEDGE, knowledgeBean)
            knowledgeDetailFragment.arguments = bundle
            return knowledgeDetailFragment
        }
    }


}