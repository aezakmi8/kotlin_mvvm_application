package com.nefidov.kotlin_mvvm_application.features.paging.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nefidov.kotlin_mvvm_application.features.paging.data.dataSources.UnsplashApi
import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinViewModel
class UnsplashViewModel : ViewModel(), KoinComponent {
    private val _imagesState: MutableStateFlow<PagingData<UnsplashImage>> = MutableStateFlow(value = PagingData.empty())
    val imagesState: MutableStateFlow<PagingData<UnsplashImage>> get() = _imagesState

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            val unsplashApi : UnsplashApi by inject()
            Pager(
                config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                pagingSourceFactory = { UnsplashPagingSource(unsplashApi) }
            ).flow
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _imagesState.value = it
                }
        }
    }
}