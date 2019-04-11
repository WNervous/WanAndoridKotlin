package com.wys.wankotlinpractice.login

import android.os.Bundle
import android.view.View
import com.wys.wankotlinpractice.R
import com.wys.wankotlinpractice.base.BaseActivity
import com.wys.wankotlinpractice.login.contract.LoginContract
import com.wys.wankotlinpractice.login.presenter.LoginPresenter
import com.wys.wankotlinpractice.utils.ToastHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    lateinit var loginPresenter: LoginPresenter
    override fun contentLayoutId(): Int = R.layout.activity_login

    override fun init(savedInstanceState: Bundle?) {
        loginPresenter = LoginPresenter(this)
        setListener()
        autoLogin()
    }

    private fun setListener() {
        registerHint.setOnClickListener {
            login.visibility = View.GONE
            registerGroup.visibility = View.VISIBLE
        }
        noLogin.setOnClickListener {
            //            MainActivity.open(this)
            finish()
        }
        login.setOnClickListener {
            // 登陆
            loginPresenter.login(userName.text.toString(), password.text.toString())
        }
        register.setOnClickListener {
            // 注册
            loginPresenter.register(userName.text.toString(), password.text.toString(), rePassWord.text.toString())
        }
    }

    private fun autoLogin() {

    }

    override fun loginSuccess() {
        ToastHelper.showLong("login success")
    }

    override fun loginFail(e: Throwable) {
        ToastHelper.showLong(e.toString())
    }

    override fun registerSuccess() {
        registerGroup.visibility = View.GONE
        login.visibility = View.VISIBLE
        ToastHelper.showShort("success")
    }

    override fun registerFail(e: Throwable) {
        ToastHelper.showLong(e.toString())
    }

    override fun isActive(): Boolean = isFinishing

}