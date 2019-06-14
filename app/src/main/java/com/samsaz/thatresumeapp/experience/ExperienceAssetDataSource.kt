package com.samsaz.thatresumeapp.experience

import android.content.res.AssetManager
import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.util.MyJsonParser
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.model.Experience
import javax.inject.Inject

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

class ExperienceAssetDataSource @Inject constructor(
    val jsonParser: MyJsonParser,
    val assets: AssetManager
): ExperienceDataSource {

    override suspend fun getData(cacheMode: CacheMode): Result<List<Experience>> {
        if (cacheMode == CacheMode.Network) {
            throw IllegalArgumentException("AssetDataStore can't provide network data")
        }
        val inputStream = assets.open("experiences.json")
        return jsonParser.parseList(inputStream, Experience::class.java)
    }

}