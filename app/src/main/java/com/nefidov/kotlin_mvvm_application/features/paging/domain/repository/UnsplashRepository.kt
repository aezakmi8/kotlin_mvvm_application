package com.nefidov.kotlin_mvvm_application.features.paging.domain.repository

import androidx.paging.PagingData
import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
    suspend fun getImages(): Flow<PagingData<UnsplashImage>>
}