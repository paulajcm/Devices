package com.github.paulajcm.devices.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.paulajcm.devices.datasource.repository.Result
import com.github.paulajcm.devices.domain.entities.mockDevicesList
import com.github.paulajcm.devices.domain.usecases.GetDevicesUseCase
import com.github.paulajcm.devices.presentation.UIState
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: GetDevicesUseCase
    @Mock
    private lateinit var devicesLiveDataObserver: Observer<UIState>
    private lateinit var viewModel: HomeViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given use case has a successful result, when retrieving devices, then should send success state`(): Unit = runBlocking {
        whenever(useCase.invoke()).thenReturn(Result.Success(mockDevicesList))

        viewModel.devicesState.observeForever(devicesLiveDataObserver)
        viewModel.retrieveDevices()

        verify(devicesLiveDataObserver).onChanged(UIState.Loaded(mockDevicesList))
    }

    @Test
    fun `when retrieving devices, then should send states for loading`(): Unit = runBlocking {

        viewModel.devicesState.observeForever(devicesLiveDataObserver)
        viewModel.retrieveDevices()

        val inOrder = inOrder(devicesLiveDataObserver)
        inOrder.verify(devicesLiveDataObserver).onChanged(UIState.Loading(true))
        inOrder.verify(devicesLiveDataObserver).onChanged(UIState.Loading(false))
    }

    @Test
    fun `given use case has an failure result, when retrieving devices, then should send error state`(): Unit = runBlocking {
        whenever(useCase.invoke()).thenReturn(Result.Failure(Exception()))

        viewModel.devicesState.observeForever(devicesLiveDataObserver)
        viewModel.retrieveDevices()

        verify(devicesLiveDataObserver).onChanged(UIState.Error)
    }

}