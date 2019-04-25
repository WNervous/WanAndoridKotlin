package com.wys.wankotlinpractice.net.observer

import com.socks.library.KLog
import com.wys.wankotlinpractice.net.bean.CommonResponse

open class NoneObserver<T> : BaseObserver<CommonResponse<T>>() {

    override fun onSuccess(t: CommonResponse<T>) {
        KLog.d(t)
    }

    override fun onFailure(e: Throwable) {
        KLog.e(e)
    }
}