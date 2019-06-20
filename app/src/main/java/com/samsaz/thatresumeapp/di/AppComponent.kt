package com.samsaz.thatresumeapp.di

import com.samsaz.shared.di.module.SharedModule
import com.samsaz.shared.di.viewmodel.ViewModelModule
import com.samsaz.thatresumeapp.MyApplication
import com.samsaz.thatresumeapp.data.ApiModule
import com.samsaz.thatresumeapp.util.analytics.AnalyticsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    ActivityBindingModule::class,
    ApiModule::class,
    SharedModule::class,
    AnalyticsModule::class]
)
interface AppComponent: AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<MyApplication> {
        override fun create(@BindsInstance app: MyApplication): AndroidInjector<MyApplication>
    }
}