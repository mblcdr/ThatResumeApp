package com.samsaz.shared.di.data

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

interface DataSource<T> {
    fun getData(): T
}