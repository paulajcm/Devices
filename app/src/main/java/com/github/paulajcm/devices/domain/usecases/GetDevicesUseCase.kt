package com.github.paulajcm.devices.domain.usecases

import com.github.paulajcm.devices.datasource.repository.Result
import com.github.paulajcm.devices.domain.entities.Device

interface GetDevicesUseCase {
    suspend operator fun invoke(): Result<List<Device>>
}