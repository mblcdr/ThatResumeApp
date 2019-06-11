package com.samsaz.thatresumeapp.experience

import android.content.res.AssetManager
import com.samsaz.thatresumeapp.model.Experience
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.Okio
import javax.inject.Inject

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

class ExperienceAssetDataSource @Inject constructor(
    val moshi: Moshi,
    val assets: AssetManager
): ExperienceDataSource {

    override fun getData(): List<Experience> {
        val buffer = Okio.buffer(Okio.source(assets.open("experiences.json")))
        val type = Types.newParameterizedType(List::class.java, Experience::class.java)
        val adapter: JsonAdapter<List<Experience>> = moshi.adapter(type)
        val list = adapter.fromJson(buffer)
        return list ?: emptyList()
    }

}