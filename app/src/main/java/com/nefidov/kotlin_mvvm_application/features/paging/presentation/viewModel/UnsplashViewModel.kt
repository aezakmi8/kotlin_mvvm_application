package com.nefidov.kotlin_mvvm_application.features.paging.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import com.nefidov.kotlin_mvvm_application.features.paging.domain.repository.UnsplashRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.component.KoinComponent

@KoinViewModel
class UnsplashViewModel(private val repository: UnsplashRepository) : ViewModel(), KoinComponent {
    private val _imagesState: MutableStateFlow<PagingData<UnsplashImage>> = MutableStateFlow(value = PagingData.empty())
    val imagesState: StateFlow<PagingData<UnsplashImage>> get() = _imagesState

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getImages()
    }

    fun setLoading() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)
            _isLoading.value = false
        }
    }

    private fun getImages() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getImages()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _imagesState.value = it
                    _isLoading.value = false
                }
        }
    }

    fun refresh() {
        getImages()
    }
}
