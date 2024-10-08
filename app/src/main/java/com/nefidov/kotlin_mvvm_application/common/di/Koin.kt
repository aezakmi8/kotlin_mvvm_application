package com.nefidov.kotlin_mvvm_application.common.di

import android.app.Application
import com.nefidov.kotlin_mvvm_application.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

fun initKoin(application: Application) =
    startKoin {
        androidLogger()
        androidContext(application)
        modules(
            MainModule().module,
            RetrofitModule().module,
            TokenProviderModule().module,
            ApiModule().module
        )
    }
