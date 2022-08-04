package com.konopelko.skooving.data.utils

sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()

    open class Error: Result<Nothing>() {
        class UnknownError : Error()
        class NetworkError : Error()
    }

    suspend fun onSuccess(function: suspend (result: Success<T>) -> Unit): Result<T> {
        if(this is Success)
            function.invoke(this)
        return this
    }

    suspend fun <G> map(function: suspend (result: Success<T>) -> Success<G>): Success<G> {
        return function.invoke(this as Success<T>)
    }

    suspend fun onError(function: suspend (result: Error) -> Unit): Result<T> {
        if(this is Error)
            function.invoke(this)
        return this
    }
}






