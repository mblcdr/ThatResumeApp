package com.samsaz.thatresumeapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Social(val name: String, val imageLink: String? = null, val link: String?)