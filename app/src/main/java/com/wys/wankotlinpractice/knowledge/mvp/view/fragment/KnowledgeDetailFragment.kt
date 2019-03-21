package com.wys.wankotlinpractice.knowledge.mvp.view.fragment

import android.os.Bundle
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_knowledge_detail.*

class KnowledgeDetailFragment : BaseFragment() {

    override fun getContentViewId(): Int = R.layout.fragment_knowledge_detail

    override fun init(savedInstanceState: Bundle?) {
//        viewPager.adapter = KnowledgeAdapter()
        slidingTabLayout.setViewPager(viewPager)
    }

    companion object {
        fun newInstace(): KnowledgeDetailFragment {
            val knowledgeDetailFragment = KnowledgeDetailFragment()
            val bundle = Bundle()
            return bundle.let {
                knowledgeDetailFragment.arguments = it
                knowledgeDetailFragment
            }


//            val knowledgeDetailFragment = KnowledgeDetailFragment()
//            return knowledgeDetailFragment.also {
//                val bundle = Bundle()
//                bundle.putString("", "")
//                knowledgeDetailFragment.arguments = bundle
//            }
        }
    }

}