package com.github.paulajcm.devices.datasource.api

import com.github.paulajcm.devices.domain.entities.Device
import retrofit2.http.GET

interface DevicesApi {

    @GET("v1/devices")
    fun getDevices(): List<Device>
}