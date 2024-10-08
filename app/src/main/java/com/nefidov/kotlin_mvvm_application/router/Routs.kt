package com.nefidov.kotlin_mvvm_application.router

import kotlinx.serialization.Serializable

@Serializable
object MainRoute

@Serializable
object OtherListRoute

@Serializable
object PagingRoute

@Serializable
data class OtherDetailRoute(
    val id: Int
)

@Serializable
object LoginScreenRoute
