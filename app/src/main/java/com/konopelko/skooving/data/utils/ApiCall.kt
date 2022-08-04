package com.konopelko.skooving.data.utils

import com.konopelko.skooving.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

suspend fun <T> apiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Result<T> =
    withContext(dispatcher) {
        try {
            Result.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            if(BuildConfig.DEBUG)
                throwable.printStackTrace()
            when (throwable) {
                is IOException, is ConnectException, is HttpException -> Result.Error.NetworkError()
                else -> Result.Error.UnknownError()
            }
        }
    }
