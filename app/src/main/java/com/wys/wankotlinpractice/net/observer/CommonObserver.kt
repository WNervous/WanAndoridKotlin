package com.wys.wankotlinpractice.net.observer

import com.wys.wankotlinpractice.net.bean.CommonResponse

abstract class CommonObserver<T> : BaseObserver<CommonResponse<T>>() {
    override fun onNext(t: CommonResponse<T>) {
       if (t.isSuccess()) onSuccess(t) else onFailure(Throwable(t.errorMsg))
    }
}