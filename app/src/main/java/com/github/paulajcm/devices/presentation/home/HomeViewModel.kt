package com.github.paulajcm.devices.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.paulajcm.devices.datasource.repository.Result
import com.github.paulajcm.devices.domain.entities.Device
import com.github.paulajcm.devices.domain.usecases.GetDevicesUseCase
import com.github.paulajcm.devices.presentation.UIState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: GetDevicesUseCase
) : ViewModel() {

    private val _devicesState = MutableLiveData<UIState>()
    val devicesState: LiveData<UIState> = _devicesState

    fun retrieveDevices(searchQuery: String = "") {

        if(!isQueryValid(searchQuery)) return

        _devicesState.value = UIState.Loading(true)
        viewModelScope.launch {
            when(val result = useCase.invoke(searchQuery)) {
                is Result.Success -> handleSuccess(result.value)
                is Result.Failure -> handleError()
            }
            _devicesState.value = UIState.Loading(false)
        }
    }

    private fun isQueryValid(query: String): Boolean {
        return query.trim().isEmpty() || query.trim().length > 3
    }

    private fun handleError() {
        _devicesState.value = UIState.Error
    }

    private fun handleSuccess(devices: List<Device>) {
        if(devices.isEmpty()) {
            _devicesState.value = UIState.Empty
        } else {
            _devicesState.value = UIState.Loaded(devices)
        }
    }
}