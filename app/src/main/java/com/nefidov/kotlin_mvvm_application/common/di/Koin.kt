package com.nefidov.kotlin_mvvm_application.common.di

import com.nefidov.kotlin_mvvm_application.MainActivity
import com.nefidov.kotlin_mvvm_application.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module

fun initKoin(mainActivity: MainActivity, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        androidContext(mainActivity)
        androidLogger()
        modules(
            MainModule().module,
            RetrofitModule().module,
            ApiModule().module
        )
    }
