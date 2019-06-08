package com.samsaz.thatresumeapp.base.ui

import androidx.lifecycle.MutableLiveData

interface ListViewStateProvider<T> {
    val listData: MutableLiveData<List<T>>
    val loadingState: MutableLiveData<ViewLoadingState>

    fun refresh()
}