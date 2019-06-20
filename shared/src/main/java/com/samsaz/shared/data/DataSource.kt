package com.samsaz.shared.data

import com.samsaz.shared.util.Result

interface DataSource<T> {
    suspend fun getData(cacheMode: CacheMode): Result<T>
}