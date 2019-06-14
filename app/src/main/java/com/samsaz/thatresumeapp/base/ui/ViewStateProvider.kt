package com.samsaz.thatresumeapp.base.ui

import androidx.lifecycle.MutableLiveData

interface ViewStateProvider<T> {
    val liveData: MutableLiveData<T>
    val loadingLiveData: MutableLiveData<ViewLoadingState>

    fun refresh()
}