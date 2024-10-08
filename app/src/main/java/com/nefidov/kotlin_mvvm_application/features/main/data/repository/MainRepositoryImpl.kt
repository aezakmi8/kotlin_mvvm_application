package com.nefidov.kotlin_mvvm_application.features.main.data.repository

import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.TokenProvider
import com.nefidov.kotlin_mvvm_application.features.main.domain.repository.MainRepository
import org.koin.core.annotation.Factory

@Factory
class MainRepositoryImpl(
    private val tokenProvider: TokenProvider
) : MainRepository {
    override fun fetchData() {
        println("fetchData")
    }

    override fun logout() {
        tokenProvider.logout()
    }
}