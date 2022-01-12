package com.github.paulajcm.devices.datasource.repository

import com.github.paulajcm.devices.domain.entities.Device

interface DevicesRepository {
    suspend fun getAllDevices(): Result<List<Device>>
    suspend fun queryDevices(query: String): Result<List<Device>>
}