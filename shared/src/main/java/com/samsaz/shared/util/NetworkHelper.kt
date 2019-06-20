package com.samsaz.shared.util

interface NetworkHelper {
    fun isConnected(): Boolean
    fun getNetworkError(): String
}