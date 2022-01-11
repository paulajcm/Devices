package com.github.paulajcm.devices.presentation.di

import com.github.paulajcm.devices.domain.usecases.GetDevicesUseCase
import com.github.paulajcm.devices.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { provideHomeViewModel(get())}
}

fun provideHomeViewModel(useCase : GetDevicesUseCase): HomeViewModel {
    return HomeViewModel(useCase)
}