package com.samsaz.shared.util

import android.content.Context
import android.net.ConnectivityManager
import com.samsaz.shared.R
import javax.inject.Inject

class RealNetworkHelper @Inject constructor(val context: Context) :
    NetworkHelper {

    val connectivityManager: ConnectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnected(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }

    override fun getNetworkError(): String = if (isConnected()) {
        context.getString(R.string.connectionError)
    } else {
        context.getString(R.string.youAreOffline)
    }
}
