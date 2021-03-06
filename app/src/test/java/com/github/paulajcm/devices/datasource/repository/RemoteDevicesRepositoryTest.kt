package com.github.paulajcm.devices.datasource.repository

import com.github.paulajcm.devices.datasource.api.DevicesApi
import com.github.paulajcm.devices.domain.entities.mockDevicesList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDevicesRepositoryTest{

    @Mock
    private lateinit var api: DevicesApi

    private lateinit var repository: RemoteDevicesRepository

    @Before
    fun setup() {
        repository = RemoteDevicesRepository(api)
    }

    @Test
    fun `when calling getDevices, then should access the api`(): Unit = runBlocking {

        repository.getAllDevices()

        verify(api).getDevices()
    }

    @Test
    fun `given a response json, when calling getDevices, then should retrieve list of devices`() = runBlocking {

        whenever(api.getDevices()).thenReturn(mockDevicesList)

        when(val result = repository.getAllDevices()) {
            is Result.Success -> assert(result.value == mockDevicesList)
        }
    }

}