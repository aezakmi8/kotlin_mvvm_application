package com.nefidov.kotlin_mvvm_application.features.main.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.nefidov.kotlin_mvvm_application.features.main.domain.repository.MainRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    init {
        mainRepository.fetchData()
    }

    fun logout() {
        mainRepository.logout()
    }
}