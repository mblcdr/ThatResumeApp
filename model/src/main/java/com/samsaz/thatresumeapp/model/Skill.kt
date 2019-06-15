package com.samsaz.thatresumeapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Skill(
    val name: String, val imageLink: String?, val description: String,
    val color: String?
)