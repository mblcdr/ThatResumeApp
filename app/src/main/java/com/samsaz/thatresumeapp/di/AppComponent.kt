package com.samsaz.thatresumeapp.di

import com.samsaz.shared.di.viewmodel.ViewModelModule
import com.samsaz.thatresumeapp.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    ActivityBindingModule::class])
interface AppComponent: AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<MyApplication> {
        override fun create(@BindsInstance app: MyApplication): AndroidInjector<MyApplication>
    }
}