package com.nefidov.kotlin_mvvm_application.features.paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nefidov.kotlin_mvvm_application.features.paging.data.dataSources.UnsplashApi
import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import com.nefidov.kotlin_mvvm_application.features.paging.domain.repository.UnsplashRepository
import com.nefidov.kotlin_mvvm_application.features.paging.domain.repository.UnsplashPagingSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class UnsplashRepositoryImpl(private val unsplashApi: UnsplashApi) : UnsplashRepository {
    override suspend fun getImages(): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = true),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi) }
        ).flow
    }
}