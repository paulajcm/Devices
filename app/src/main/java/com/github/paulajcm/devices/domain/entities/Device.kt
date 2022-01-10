package com.github.paulajcm.devices.domain.entities

data class Device(
    val id: String,
    val type: String,
    val price: Float,
    val currency: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val title: String,
    val description: String
)
