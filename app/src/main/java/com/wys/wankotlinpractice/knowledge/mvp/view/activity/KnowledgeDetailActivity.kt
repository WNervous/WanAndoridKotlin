package com.wys.wankotlinpractice.knowledge.mvp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseActivity
import com.wys.wankotlinpractice.knowledge.mvp.view.fragment.KnowledgeDetailFragment

class KnowledgeDetailActivity : BaseActivity() {
    private lateinit var fragment: KnowledgeDetailFragment

    override fun contentLayoutId(): Int = R.layout.activity_knowledge_detail

    override fun init(savedInstanceState: Bundle?) {
        fragment = if (savedInstanceState == null) {
            KnowledgeDetailFragment()
        } else {
            supportFragmentManager.getFragment(
                savedInstanceState,
                KnowledgeDetailFragment::class.java.name
            ) as KnowledgeDetailFragment
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commitAllowingStateLoss()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let { supportFragmentManager.putFragment(it, KnowledgeDetailFragment::class.java.name, fragment) }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, KnowledgeDetailActivity::class.java))
        }
    }
}