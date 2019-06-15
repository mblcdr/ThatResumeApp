package com.samsaz.thatresumeapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AboutMe(
    val name: String, val occupation: String, val imageLink: String?,
    val aboutMe: String
)