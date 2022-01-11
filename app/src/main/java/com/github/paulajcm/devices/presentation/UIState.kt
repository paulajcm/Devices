package com.github.paulajcm.devices.presentation

sealed class UIState {
    data class Loading(val isLoading : Boolean) : UIState()
    data class Loaded<T>(val value: T) : UIState()
    object Error : UIState()
    object Empty : UIState()
}
