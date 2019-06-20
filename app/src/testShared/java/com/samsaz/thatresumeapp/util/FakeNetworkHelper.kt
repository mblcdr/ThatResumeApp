package com.samsaz.thatresumeapp.util

import com.samsaz.shared.util.NetworkHelper

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

class FakeNetworkHelper : NetworkHelper {
    override fun isConnected() = true
    override fun getNetworkError() = "Connection Error"
}