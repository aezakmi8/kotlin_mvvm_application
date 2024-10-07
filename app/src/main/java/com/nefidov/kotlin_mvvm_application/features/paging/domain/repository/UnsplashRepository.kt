package com.nefidov.kotlin_mvvm_application.features.paging.domain.repository

import androidx.paging.PagingData
import com.nefidov.kotlin_mvvm_application.features.paging.domain.entities.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
    suspend fun getImages(): Flow<PagingData<UnsplashImage>>
}