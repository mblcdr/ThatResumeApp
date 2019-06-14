package com.samsaz.thatresumeapp.data

import com.samsaz.shared.data.CacheMode
import com.samsaz.thatresumeapp.model.Experience
import retrofit2.http.GET
import retrofit2.http.Tag

interface Api {

    @GET("experiences")
    suspend fun getExperiences(@Tag cacheMode: CacheMode = CacheMode.Network): List<Experience>

}