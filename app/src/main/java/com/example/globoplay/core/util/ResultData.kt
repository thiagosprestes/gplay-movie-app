package com.example.globoplay.core.util


enum class ErrorType {
    NETWORK,
    GENERIC
}

sealed class ResultData<out T>() {
    data class Success<out T>(val data: T?) : ResultData<T>()
    data class Failure(val e: Exception?, val type: ErrorType) : ResultData<Nothing>()
}