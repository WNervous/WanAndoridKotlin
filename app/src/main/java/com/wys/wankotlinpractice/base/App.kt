package com.wys.wankotlinpractice.base

import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.multidex.MultiDex
import com.tencent.mmkv.MMKV

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        content = applicationContext
        MMKV.initialize(this)
        MultiDex.install(this)
    }

    companion object {

        lateinit var content: Context

        private lateinit var sPackageInfo: PackageInfo

        fun getVersionName(): String {
            initPackageInfo()
            return sPackageInfo.versionName
        }

//        fun getVersionCode(): Int {
//            initPackageInfo()
//            return sPackageInfo.versionCode
//        }

        fun getAppPackageName(): String {
            initPackageInfo()
            return sPackageInfo.packageName
        }

        private fun initPackageInfo() {
            try {
                sPackageInfo = content.packageManager.getPackageInfo(content.packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                throw RuntimeException("NameNotFoundException when querying own package. Should not happen", e)
            }
        }
    }


}