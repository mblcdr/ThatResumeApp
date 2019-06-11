package com.samsaz.shared.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */
@Module
class MoshiModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

}