package com.nefidov.kotlin_mvvm_application

import android.app.Application
import com.nefidov.kotlin_mvvm_application.common.di.initKoin
import org.koin.core.module.KoinApplicationDslMarker

@KoinApplicationDslMarker
class App : Application(){
    override fun onCreate() {
        super.onCreate()

        initKoin(this@App)
    }
}