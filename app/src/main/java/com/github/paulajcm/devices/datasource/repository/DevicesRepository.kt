package com.github.paulajcm.devices.datasource.repository

import com.github.paulajcm.devices.domain.entities.Device

interface DevicesRepository {
    fun getDevices(): List<Device>
}