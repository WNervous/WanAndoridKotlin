package com.wys.wankotlinpractice.base

import android.app.Application
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log

class App : Application() {

    private lateinit var sPackageInfo: PackageInfo

    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "create")
    }

    fun getVersionName(): String {
        initPackageInfo()
        return sPackageInfo.versionName
    }

    fun getVersionCode(): Int {
        initPackageInfo()
        return sPackageInfo.versionCode
    }

    fun getAppPackageName(): String {
        initPackageInfo()
        return sPackageInfo.packageName
    }

    private fun initPackageInfo() {
        try {
            Log.d("Application", "applicationContext : ${applicationContext == null}")
            sPackageInfo = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException("NameNotFoundException when querying own package. Should not happen", e)
        }
    }
}