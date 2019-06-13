package com.samsaz.shared.di.module

import com.samsaz.shared.util.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class CoroutineDispatcherModule {

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatchers {
        return CoroutineDispatchers(
            main = Dispatchers.Main,
            io = Dispatchers.IO
        )
    }

}