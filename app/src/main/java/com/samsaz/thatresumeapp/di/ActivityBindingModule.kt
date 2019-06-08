package com.samsaz.thatresumeapp.di

import com.samsaz.shared.di.scope.ActivityScope
import com.samsaz.thatresumeapp.MainActivity
import com.samsaz.thatresumeapp.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

}