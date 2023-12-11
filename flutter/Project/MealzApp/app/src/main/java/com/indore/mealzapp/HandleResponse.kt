package com.indore.mealzapp

import retrofit2.HttpException
import retrofit2.Response


suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.ApiSuccess(body)
        } else {
            NetworkResult.ApiError(code = response.code(), message = response.message(), url = response.errorBody().toString())
        }
    } catch (e: HttpException) {
        NetworkResult.ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.ApiException(e)
    }
}

sealed class NetworkResult<T : Any> {
    data class ApiSuccess<T: Any>(val data: T) : NetworkResult<T>()
    data class ApiError<T: Any>(val code: Int, val message: String?,val url:String="") : NetworkResult<T>()
    data class ApiException<T: Any>(val e: Throwable) : NetworkResult<T>()
}