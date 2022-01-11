package com.github.paulajcm.devices.domain.di

import com.github.paulajcm.devices.datasource.repository.DevicesRepository
import com.github.paulajcm.devices.domain.usecases.GetDevicesUseCase
import com.github.paulajcm.devices.domain.usecases.GetDevicesUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory { provideGetDevicesUseCase(get())}
}

fun provideGetDevicesUseCase(repository: DevicesRepository): GetDevicesUseCase {
    return GetDevicesUseCaseImpl(repository)
}