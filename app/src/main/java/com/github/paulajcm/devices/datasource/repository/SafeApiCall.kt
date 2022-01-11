package com.github.paulajcm.devices.datasource.repository

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): Result<T> {
    return try {
        val response = apiCall()
        Result.Success(response)
    } catch (e: HttpException) {
        Result.Failure(e)
    } catch (e: ConnectException) {
        Result.Failure(e)
    } catch (e: SocketTimeoutException) {
        Result.Failure(e)
    } catch (e: UnknownHostException) {
        Result.Failure(e)
    }
}