package com.nefidov.kotlin_mvvm_application.common.data.tokenProvider

import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.models.AuthState
import kotlinx.coroutines.flow.StateFlow

interface TokenProvider {
    val isAuthenticated: StateFlow<AuthState>

    fun login()
    
    fun logout()
}

