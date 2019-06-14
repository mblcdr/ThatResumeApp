package com.samsaz.thatresumeapp.data

import com.samsaz.shared.data.CacheMode
import com.samsaz.thatresumeapp.model.AboutMe
import com.samsaz.thatresumeapp.model.Experience
import com.samsaz.thatresumeapp.model.Skill
import retrofit2.http.GET
import retrofit2.http.Tag

interface Api {

    @GET("experiences")
    suspend fun getExperiences(@Tag cacheMode: CacheMode = CacheMode.Network): List<Experience>

    @GET("skills")
    suspend fun getSkills(@Tag cacheMode: CacheMode = CacheMode.Network): List<Skill>

    @GET("aboutme")
    suspend fun getAboutMe(@Tag cacheMode: CacheMode = CacheMode.Network): AboutMe

}