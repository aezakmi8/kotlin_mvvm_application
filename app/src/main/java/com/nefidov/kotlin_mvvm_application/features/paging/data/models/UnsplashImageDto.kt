package com.nefidov.kotlin_mvvm_application.features.paging.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImage(
    val id: String,
    val description: String?,
    val urls: UnsplashImageUrls
)