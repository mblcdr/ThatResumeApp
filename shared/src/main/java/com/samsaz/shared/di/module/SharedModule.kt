package com.samsaz.shared.di.module

import dagger.Module

@Module(includes = [CoroutineDispatcherModule::class, MoshiModule::class])
class SharedModule