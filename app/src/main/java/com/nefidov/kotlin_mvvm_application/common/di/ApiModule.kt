package com.nefidov.kotlin_mvvm_application.common.di

import com.nefidov.kotlin_mvvm_application.features.paging.data.dataSources.UnsplashApi
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import retrofit2.Retrofit
import retrofit2.create

@Module
class ApiModule {
    @Factory
    fun UnsplashApi(retrofit: Retrofit) = retrofit.create<UnsplashApi>()
}