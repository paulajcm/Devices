package com.github.paulajcm.devices.datasource.api

import com.github.paulajcm.devices.domain.entities.Device
import retrofit2.http.GET
import retrofit2.http.Query

interface DevicesApi {

    @GET("v1/devices")
    suspend fun getDevices(): List<Device>

    @GET("v1/devices/query")
    suspend fun queryDevices(@Query("name") query: String): List<Device>
}