package com.samsaz.thatresumeapp.util

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

fun provideFakeNetworkHelper() = mock<NetworkHelper> {
    on { isConnected() } doReturn true
    on { getNetworkError() } doReturn "Connection Error"
}