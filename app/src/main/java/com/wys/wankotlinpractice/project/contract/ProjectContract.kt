package com.wys.wankotlinpractice.project.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView
import com.wys.wankotlinpractice.project.model.ProjectBean

class ProjectContract {
    interface View : IView {
        fun showProject(list: MutableList<ProjectBean>)
        fun onFailed()
    }

    interface Presenter : IPresenter {
        fun getProject()
    }
}