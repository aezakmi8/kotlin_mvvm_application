package com.nefidov.kotlin_mvvm_application.common.data.tokenProvider

import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.models.AuthState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class TokenProviderImpl() : TokenProvider {
    private val _isAuthenticated = MutableStateFlow<AuthState>(value = AuthState.Unknown)

    override val isAuthenticated: StateFlow<AuthState> = _isAuthenticated.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        coroutineScope.launch {
            _isAuthenticated.emit(AuthState.NotAuthenticated)
        }
    }

    override fun login() {
        coroutineScope.launch {
            _isAuthenticated.emit(AuthState.Authenticated)
        }
    }

    override fun logout() {
        coroutineScope.launch {
            _isAuthenticated.emit(AuthState.NotAuthenticated)
        }
    }
}

