package com.nefidov.kotlin_mvvm_application.features.paging.domain.entities

import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
data class UnsplashImage @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String,
    val description: String?,
    val urls: UnsplashImageUrls,
    val uuid: String = Uuid.random().toString()
)