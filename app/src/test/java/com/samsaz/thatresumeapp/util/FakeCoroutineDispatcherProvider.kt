package com.samsaz.thatresumeapp.util

import com.samsaz.shared.util.CoroutineDispatchers
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

/**
 * Copyright 2018
 * Created and maintained by Hamid Moazzami
 */


@UseExperimental(ExperimentalCoroutinesApi::class)
fun provideFakeCoroutineDispatchers(): CoroutineDispatchers {
    return CoroutineDispatchers(
        main = Dispatchers.Unconfined,
        io = Dispatchers.Unconfined
    )
}

fun provideNoDelayCoroutineDispatchers(): CoroutineDispatchers {
    return CoroutineDispatchers(
        main = NoDelayTestDispatcher(),
        io = NoDelayTestDispatcher()
    )
}


@UseExperimental(InternalCoroutinesApi::class)
class NoDelayTestDispatcher : CoroutineDispatcher(), Delay {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()
    }

    override fun scheduleResumeAfterDelay(
        timeMillis: Long,
        continuation: CancellableContinuation<Unit>
    ) {
        continuation.resume(Unit)
    }

}