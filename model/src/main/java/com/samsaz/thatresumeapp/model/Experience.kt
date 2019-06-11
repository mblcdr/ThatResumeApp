package com.samsaz.thatresumeapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Experience(
    val duration: String, val title: String, val description: String, val companyName: String?,
    val companyImageUrl: String?
)