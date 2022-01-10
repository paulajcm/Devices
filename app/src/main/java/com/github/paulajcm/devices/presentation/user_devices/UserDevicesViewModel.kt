package com.github.paulajcm.devices.presentation.user_devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDevicesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Coming soon - My devices"
    }
    val text: LiveData<String> = _text
}