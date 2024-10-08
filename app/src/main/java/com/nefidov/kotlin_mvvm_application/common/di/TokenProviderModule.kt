package com.nefidov.kotlin_mvvm_application.common.di

import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.TokenProvider
import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.TokenProviderImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class TokenProviderModule {
    @Single
    fun initTokenProvider(): TokenProvider {
        return TokenProviderImpl()
    }
}
