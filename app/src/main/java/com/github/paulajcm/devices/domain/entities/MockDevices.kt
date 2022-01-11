package com.github.paulajcm.devices.domain.entities

val device1 = Device(
    id = "1",
    type = "Sensor",
    price = 20.0,
    currency = "USD",
    isFavorite = false,
    imageUrl = "",
    title = "Test Sensor",
    description = ""
)

val device2 = Device(
    id = "1",
    type = "Thermostat",
    price = 25.0,
    currency = "USD",
    isFavorite = false,
    imageUrl = "",
    title = "Test Thermostat",
    description = ""
)

val mockDevicesList = listOf(device1, device2)