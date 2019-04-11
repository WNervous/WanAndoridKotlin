package com.wys.wankotlinpractice.login.contract

import com.wys.wankotlinpractice.base.IPresenter
import com.wys.wankotlinpractice.base.IView

class LoginContract {

    interface View : IView {
        fun loginSuccess()
        fun loginFail(e: Throwable)
        fun registerSuccess()
        fun registerFail(e: Throwable)
    }

    interface Presenter : IPresenter {
        fun login(userName: String, password: String)
        fun register(userName: String, password: String, repassword: String)
    }
}