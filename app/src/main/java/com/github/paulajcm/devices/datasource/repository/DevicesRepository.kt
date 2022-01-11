package com.github.paulajcm.devices.datasource.repository

import com.github.paulajcm.devices.domain.entities.Device

interface DevicesRepository {
    suspend fun getDevices(): Result<List<Device>>
}