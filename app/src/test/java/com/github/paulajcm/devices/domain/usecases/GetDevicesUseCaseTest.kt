package com.github.paulajcm.devices.domain.usecases

import com.github.paulajcm.devices.datasource.repository.DevicesRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDevicesUseCaseTest {

    @Mock
    private lateinit var repository: DevicesRepository
    private lateinit var useCase: GetDevicesUseCase

    @Before
    fun setup() {
        useCase = GetDevicesUseCaseImpl(repository)
    }

    @Test
    fun `when invoking the use case, should access the repository`(): Unit = runBlocking {

        useCase()

        verify(repository).getDevices()
    }
}