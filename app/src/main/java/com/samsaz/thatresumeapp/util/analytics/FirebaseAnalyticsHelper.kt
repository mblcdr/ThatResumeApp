package com.samsaz.thatresumeapp.util.analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class FirebaseAnalyticsHelper @Inject constructor(context: Context) :
    AnalyticsHelper {

    private val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun sendScreenView(screenName: String, activity: Activity) {
        analytics.setCurrentScreen(activity, screenName, null)
    }

    override fun sendEvent(eventName: String, params: Map<String, Any>?) {
        val bundleParams = Bundle().apply {
            params?.forEach {
                if (it.value is String)
                    putString(it.key, it.value as String)
                else if (it.value is Number)
                    putDouble(it.key, (it.value as Number).toDouble())
            }
        }
        analytics.logEvent(eventName, bundleParams)
    }

}