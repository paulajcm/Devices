package com.github.paulajcm.devices.datasource.di

import com.github.paulajcm.devices.BuildConfig
import com.github.paulajcm.devices.datasource.api.DevicesApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val datasourceModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideDevicesApi(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideDevicesApi(retrofit: Retrofit): DevicesApi {
    return retrofit.create(DevicesApi::class.java)
}