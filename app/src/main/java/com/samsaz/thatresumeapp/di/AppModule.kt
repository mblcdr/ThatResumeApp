package com.samsaz.thatresumeapp.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun providesApplicationContext(application: Application): Context
}