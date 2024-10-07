package com.nefidov.kotlin_mvvm_application.features.paging.domain.entities

import androidx.compose.runtime.Immutable

@Immutable
data class UnsplashImageUrls(
    val regular: String,
    val small: String
)