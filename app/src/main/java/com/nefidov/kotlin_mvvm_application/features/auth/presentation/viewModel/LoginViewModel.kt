package com.nefidov.kotlin_mvvm_application.features.paging.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.TokenProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val tokenProvider: TokenProvider
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login() {
        viewModelScope.launch {
            _isLoading.value = true

            delay(2000)
            tokenProvider.login()

            _isLoading.value = false
        }
    }
}
