package com.samsaz.thatresumeapp.util

import android.content.Context
import android.net.ConnectivityManager
import com.samsaz.thatresumeapp.R
import javax.inject.Inject

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

open class NetworkHelper @Inject constructor(val context: Context) {

    val connectivityManager: ConnectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isConnected(): Boolean = connectivityManager.activeNetworkInfo?.isConnected ?: false

    fun getNetworkError(): String = if (isConnected()) {
        context.getString(R.string.connectionError)
    } else {
        context.getString(R.string.youAreOffline)
    }
}
