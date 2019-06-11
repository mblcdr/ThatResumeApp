package com.samsaz.thatresumeapp.di

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.samsaz.thatresumeapp.MyApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideApplicationContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideAssetManager(context: Context): AssetManager {
        return context.assets
    }

}