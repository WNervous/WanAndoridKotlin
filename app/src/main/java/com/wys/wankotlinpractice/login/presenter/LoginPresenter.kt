package com.wys.wankotlinpractice.login.presenter

import com.wys.wankotlinpractice.login.contract.LoginContract
import com.wys.wankotlinpractice.login.manager.User
import com.wys.wankotlinpractice.login.manager.UserManager
import com.wys.wankotlinpractice.net.ApiService
import com.wys.wankotlinpractice.net.bean.CommonResponse
import com.wys.wankotlinpractice.net.observer.CommonObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {


    override fun login(userName: String, password: String) {
//        val loginBean = LoginBean(userName, password)
        ApiService.sApi.wanApi().login2(userName, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<User>() {
                override fun onSuccess(t: CommonResponse<User>) {
                    UserManager.saveUserData(t.data)
                    view.loginSuccess()
                }

                override fun onFailure(e: Throwable) {
                    view.loginFail(e)
                }
            })
    }

    override fun register(userName: String, password: String, repassword: String) {
//        val registerBean = RegisterBean()
        ApiService.sApi.wanApi().register2(userName, password, repassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<User>() {
                override fun onSuccess(t: CommonResponse<User>) {
                    view.registerSuccess()
                }

                override fun onFailure(e: Throwable) {
                    view.registerFail(e)
                }
            })
    }

    override fun onDestroty() {

    }
}

