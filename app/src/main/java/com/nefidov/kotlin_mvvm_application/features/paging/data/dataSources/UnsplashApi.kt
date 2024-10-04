package com.nefidov.kotlin_mvvm_application.features.paging.data.dataSources

import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {
    companion object {
        const val CLIENT_ID = "yUgjt75aYFpp7tr3x8IsNaDNmRDO7ZeWZyUwUVIGmBA"
    }

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("photos")
    suspend fun searchPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashImage>
}