package com.nefidov.kotlin_mvvm_application.common.di

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit

@Module
class RetrofitModule {
    @Single
    fun retrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            //.client(get())
            .build()
    }
}

//private fun Scope.retrofitHttpClient(): OkHttpClient {
//    return OkHttpClient.Builder().apply {
//        cache(get())
//        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//        retryOnConnectionFailure(true)
//        addInterceptor(get())
//        addInterceptor(HttpLoggingInterceptor().apply {
//            level = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor.Level.HEADERS
//            }
//            else {
//                HttpLoggingInterceptor.Level.NONE
//            }
//        })
//    }.build()