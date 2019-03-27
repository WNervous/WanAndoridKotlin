package com.wys.wankotlinpractice.project.presenter

import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import com.wys.wankotlinpractice.project.contract.ProjectContract
import com.wys.wankotlinpractice.project.model.ProjectBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectPresenter(val view: ProjectContract.View) : ProjectContract.Presenter {


    override fun getProject() {
        ApiService.sApi.wanApi().getProjects()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<MutableList<ProjectBean>>() {
                override fun onSuccess(t: CommonResponse<MutableList<ProjectBean>>) {
                    view.showProject(t.data)
                }

                override fun onFailure(e: Throwable) {
                    view.onFailed()
                }

            })
    }

    override fun onDestroty() {
    }
}