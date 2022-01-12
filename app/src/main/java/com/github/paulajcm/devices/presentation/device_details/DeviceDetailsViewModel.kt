package com.github.paulajcm.devices.presentation.device_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeviceDetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Coming soon - Settings"
    }
    val text: LiveData<String> = _text
}