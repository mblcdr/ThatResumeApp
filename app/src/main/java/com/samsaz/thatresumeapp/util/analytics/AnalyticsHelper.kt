package com.samsaz.thatresumeapp.util.analytics

import android.app.Activity

interface AnalyticsHelper {
    fun sendScreenView(screenName: String, activity: Activity)
    fun sendEvent(eventName: String, params: Map<String, Any>? = null)
}

object AnalyticsConsts {
    object Events {
        const val SEARCH = "search"
    }

    object Params {
        const val QUERY = "query"
    }
}