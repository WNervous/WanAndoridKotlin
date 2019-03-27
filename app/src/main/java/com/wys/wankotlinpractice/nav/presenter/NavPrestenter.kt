package com.wys.wankotlinpractice.nav.presenter

import com.wys.wankotlinpractice.nav.contract.NavContract
import com.wys.wankotlinpractice.nav.model.NavBean
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NavPrestenter(val view: NavContract.View) : NavContract.Presenter {

    lateinit var disposed: Disposable
    override fun getNav() {
        ApiService.sApi.wanApi().getNav()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<List<NavBean>>() {
                override fun onSuccess(t: CommonResponse<List<NavBean>>) {
                    disposed = disposable
                    view.showNav(t.data)
                }

                override fun onFailure(e: Throwable) {
                    disposed = disposable
                }
            })
    }

    override fun onDestroty() {
        if (!disposed.isDisposed) {
            disposed.dispose()
        }
    }

}