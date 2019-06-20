package com.samsaz.thatresumeapp.util

import com.samsaz.shared.util.NetworkHelper

class FakeNetworkHelper : NetworkHelper {
    override fun isConnected() = true
    override fun getNetworkError() = "Connection Error"
}