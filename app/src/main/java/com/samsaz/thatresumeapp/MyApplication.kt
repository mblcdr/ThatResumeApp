package com.samsaz.thatresumeapp

import com.samsaz.thatresumeapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.factory().create(this)
}