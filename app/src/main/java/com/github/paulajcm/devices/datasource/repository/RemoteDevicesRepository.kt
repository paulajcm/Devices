package com.github.paulajcm.devices.datasource.repository

import com.github.paulajcm.devices.datasource.api.DevicesApi
import com.github.paulajcm.devices.domain.entities.Device
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RemoteDevicesRepository(
    private val api : DevicesApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DevicesRepository{
    override suspend fun getDevices(): Result<List<Device>> = withContext(dispatcher) {
        return@withContext safeApiCall { api.getDevices() }
    }
}