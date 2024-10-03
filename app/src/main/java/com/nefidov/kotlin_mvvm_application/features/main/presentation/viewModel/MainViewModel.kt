package com.nefidov.kotlin_mvvm_application.features.main.presentation.viewModel

import com.nefidov.kotlin_mvvm_application.features.main.domain.repository.MainRepository
import org.koin.android.annotation.KoinViewModel
import androidx.lifecycle.ViewModel

@KoinViewModel
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    init {
        mainRepository.fetchData()
    }
}