package com.github.paulajcm.devices.datasource.repository

import com.github.paulajcm.devices.datasource.api.DevicesApi
import com.github.paulajcm.devices.domain.entities.Device

internal class RemoteDevicesRepository(
    private val api : DevicesApi
) : DevicesRepository{
    override fun getDevices(): List<Device> {
        return api.getDevices()
    }
}