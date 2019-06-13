package com.samsaz.shared.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(protected val dispatchers: CoroutineDispatchers) : ViewModel(),
    CoroutineScope {

    private val parentJob = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + parentJob

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}