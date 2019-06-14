package com.samsaz.thatresumeapp.data

import androidx.lifecycle.MutableLiveData
import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.data.DataSource
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState

abstract class BaseCacheRepository<T> {
    protected abstract val remoteDataSource: DataSource<T>
    protected abstract val assetsDataSource: DataSource<T>

    suspend fun loadData(
        dataLiveData: MutableLiveData<T>,
        loadingLiveData: MutableLiveData<ViewLoadingState>
    ) {
        loadingLiveData.value = ViewLoadingState.Loading
        val cachedResult = remoteDataSource.getData(CacheMode.Cache)
        if (cachedResult is Result.Success) {
            dataLiveData.value = cachedResult.data
        } else {
            val assetsResult = assetsDataSource.getData(CacheMode.Cache)
            if (assetsResult is Result.Success) {
                dataLiveData.value = assetsResult.data
            }
        }

        val networkResult = remoteDataSource.getData(CacheMode.Network)
        if (networkResult is Result.Success) {
            dataLiveData.value = networkResult.data
            loadingLiveData.value = ViewLoadingState.Success
        } else {
            loadingLiveData.value = ViewLoadingState.Error("Connection Error")
        }
    }
}