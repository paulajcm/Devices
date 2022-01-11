package com.github.paulajcm.devices

import android.app.Application
import com.github.paulajcm.devices.datasource.di.datasourceModule
import com.github.paulajcm.devices.domain.di.domainModule
import com.github.paulajcm.devices.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class DevicesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@DevicesApplication)
            modules(datasourceModule + domainModule + presentationModule)
        }
    }
}