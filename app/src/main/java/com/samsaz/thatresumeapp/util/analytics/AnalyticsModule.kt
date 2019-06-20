package com.samsaz.thatresumeapp.util.analytics

import dagger.Binds
import dagger.Module

@Module
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticsHelper(analyticsHelper: FirebaseAnalyticsHelper): AnalyticsHelper

}