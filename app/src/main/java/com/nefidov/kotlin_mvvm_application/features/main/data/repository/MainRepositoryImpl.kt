package com.nefidov.kotlin_mvvm_application.features.main.data.repository

import com.nefidov.kotlin_mvvm_application.features.main.domain.repository.MainRepository
import org.koin.core.annotation.Factory

@Factory
class MainRepositoryImpl : MainRepository {
    override fun fetchData() {
        println("fetchData")
    }
}