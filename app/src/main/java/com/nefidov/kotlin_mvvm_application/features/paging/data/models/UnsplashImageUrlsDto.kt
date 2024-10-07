package com.nefidov.kotlin_mvvm_application.features.paging.data.models

import com.nefidov.kotlin_mvvm_application.features.paging.domain.entities.UnsplashImageUrls
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImageUrlsDto(
    val regular: String,
    val small: String
) {
    fun toEntity() = UnsplashImageUrls(
        regular = regular,
        small = small
    )
}