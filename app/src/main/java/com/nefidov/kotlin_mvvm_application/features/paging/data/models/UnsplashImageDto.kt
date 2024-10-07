package com.nefidov.kotlin_mvvm_application.features.paging.data.models

import com.nefidov.kotlin_mvvm_application.features.paging.domain.entities.UnsplashImage
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImageDto(
    val id: String,
    val description: String?,
    val urls: UnsplashImageUrlsDto
) {
    fun toEntity() = UnsplashImage(
        id = id,
        description = description,
        urls = urls.toEntity()
    )
}