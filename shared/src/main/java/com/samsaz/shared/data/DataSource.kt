package com.samsaz.shared.data

import com.samsaz.shared.util.Result

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

interface DataSource<T> {
    suspend fun getData(): Result<T>
}