package com.samsaz.thatresumeapp.util

import android.app.Activity
import com.samsaz.thatresumeapp.util.analytics.AnalyticsHelper

class FakeAnalyticsHelper : AnalyticsHelper {
    override fun sendScreenView(screenName: String, activity: Activity) {}

    override fun sendEvent(eventName: String, params: Map<String, Any>?) {}
}