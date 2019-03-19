package com.wys.wankotlinpractice

import android.os.Bundle
import com.wys.wankotlinpractice.base.BaseActivity

class NotificationActivity :BaseActivity(){
    override fun init(savedInstanceState: Bundle?) {
    }

    override fun contentLayoutId(): Int =R.layout.notification_layout
}