package com.github.paulajcm.devices.datasource.di

import com.github.paulajcm.devices.BuildConfig
import com.github.paulajcm.devices.datasource.api.DevicesApi
import com.github.paulajcm.devices.datasource.repository.DevicesRepository
import com.github.paulajcm.devices.datasource.repository.RemoteDevicesRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val datasourceModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideDevicesApi(get()) }

    factory { provideDevicesRepository(get())}
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().setLenient().create())
        ).build()
}

fun provideDevicesApi(retrofit: Retrofit): DevicesApi {
    return retrofit.create(DevicesApi::class.java)
}

fun provideDevicesRepository(api: DevicesApi) : DevicesRepository {
    return RemoteDevicesRepository(api)
}