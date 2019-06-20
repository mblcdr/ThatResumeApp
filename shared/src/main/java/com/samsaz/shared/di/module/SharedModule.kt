package com.samsaz.shared.di.module

import com.samsaz.shared.util.NetworkHelper
import com.samsaz.shared.util.RealNetworkHelper
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [CoroutineDispatcherModule::class, MoshiModule::class])
abstract class SharedModule {

    @Binds
    @Singleton
    abstract fun provideNetworkHelper(networkHelper: RealNetworkHelper): NetworkHelper

}