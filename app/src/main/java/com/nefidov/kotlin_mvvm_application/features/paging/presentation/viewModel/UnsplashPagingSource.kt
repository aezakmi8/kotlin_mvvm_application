package com.nefidov.kotlin_mvvm_application.features.paging.presentation.viewModel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nefidov.kotlin_mvvm_application.features.paging.data.dataSources.UnsplashApi
import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import retrofit2.HttpException
import java.io.IOException

class UnsplashPagingSource(private val unsplashApi: UnsplashApi) : PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val page = params.key ?: 1

        return try {
            val response = unsplashApi.searchPhotos(page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            println(exception.message)
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}