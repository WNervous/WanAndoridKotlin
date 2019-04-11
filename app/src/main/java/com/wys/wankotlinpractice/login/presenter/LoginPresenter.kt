package com.wys.wankotlinpractice.login.presenter

import com.wys.wankotlinpractice.login.contract.LoginContract
import com.wys.wankotlinpractice.login.model.LoginBean
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {


    override fun login(userName: String, password: String) {
        val loginBean = LoginBean(userName, password)
        ApiService.sApi.wanApi().login(loginBean)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<Any>() {
                override fun onSuccess(t: CommonResponse<Any>) {
                    view.loginSuccess()
                }

                override fun onFailure(e: Throwable) {
                    view.loginFail(e)
                }
            })
    }

    override fun register(userName: String, password: String, repassword: String) {

    }

    override fun onDestroty() {

    }
}

