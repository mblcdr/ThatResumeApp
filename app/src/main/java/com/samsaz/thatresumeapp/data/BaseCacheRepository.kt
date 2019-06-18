package com.samsaz.thatresumeapp.data

import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.data.DataSource
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.util.NetworkHelper

abstract class BaseCacheRepository<T>(val networkHelper: NetworkHelper) {
    protected abstract val remoteDataSource: DataSource<T>
    protected abstract val assetsDataSource: DataSource<T>

    suspend fun loadData(
        dataStateListener: DataStateListener<T>
    ) {
        dataStateListener.onStateChange(ViewLoadingState.Loading)
        val cachedResult = remoteDataSource.getData(CacheMode.Cache)
        if (cachedResult is Result.Success) {
            dataStateListener.onDataChange(cachedResult.data)
        } else {
            val assetsResult = assetsDataSource.getData(CacheMode.Cache)
            if (assetsResult is Result.Success) {
                dataStateListener.onDataChange(assetsResult.data)
            }
        }

        val networkResult = remoteDataSource.getData(CacheMode.Network)
        if (networkResult is Result.Success) {
            dataStateListener.onDataChange(networkResult.data)
            dataStateListener.onStateChange(ViewLoadingState.Success)
        } else {
            dataStateListener.onStateChange(ViewLoadingState.Error(networkHelper.getNetworkError()))
        }
    }
}