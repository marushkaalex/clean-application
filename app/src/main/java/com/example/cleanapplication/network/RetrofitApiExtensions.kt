package com.example.cleanapplication.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.ResponseBody
import retrofit2.Call
import kotlin.coroutines.resume

sealed class Response

data class Success<T>(val data: T) : Response()

sealed class Failure : Response()

class Exception(val exception: Exception) : Failure()

class Error(val data: ResponseBody?) : Failure()

@ExperimentalCoroutinesApi
suspend fun <T> Call<T>.exec() = suspendCancellableCoroutine<Response> { continuation ->
    val response = execute()
    continuation.resume(
        when {
            response.isSuccessful -> Success(response.body() ?: Any())
            else -> Error(response.errorBody())
        }
    )
}

@Suppress("UNCHECKED_CAST")
fun <T> Response.unseal(errorHandler: (failure: Failure) -> Unit): T? {
    return when (this) {
        is Success<*> -> { this.data as? T }
        is Failure -> {
            errorHandler(this)
            null
        }
    }
}

