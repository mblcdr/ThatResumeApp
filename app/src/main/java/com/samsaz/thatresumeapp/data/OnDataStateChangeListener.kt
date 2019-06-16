package com.samsaz.thatresumeapp.data

import com.samsaz.thatresumeapp.base.ui.ViewLoadingState

interface DataStateListener<T> {
    fun onDataChange(data: T)
    fun onStateChange(state: ViewLoadingState)
}