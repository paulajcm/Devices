package com.github.paulajcm.devices.domain.usecases

import com.github.paulajcm.devices.datasource.repository.DevicesRepository
import com.github.paulajcm.devices.datasource.repository.Result
import com.github.paulajcm.devices.domain.entities.Device

class GetDevicesUseCaseImpl(
    private val remoteDevicesRepository: DevicesRepository
) : GetDevicesUseCase {
    override suspend fun invoke(): Result<List<Device>> {
        return remoteDevicesRepository.getDevices()
    }
}