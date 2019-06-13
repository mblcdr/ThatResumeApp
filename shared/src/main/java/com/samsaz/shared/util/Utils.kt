package com.samsaz.shared.util

inline fun <R> tryResult(function: () -> R): Result<R> {
    return try {
        Result.Success(function())
    } catch (throwable: Throwable) {
        Result.Error(throwable.message ?: "")
    }
}