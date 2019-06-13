package com.samsaz.thatresumeapp.di

import android.content.Context
import android.content.res.AssetManager
import com.samsaz.thatresumeapp.MyApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApplicationContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun provideAssetManager(context: Context): AssetManager {
        return context.assets
    }
}