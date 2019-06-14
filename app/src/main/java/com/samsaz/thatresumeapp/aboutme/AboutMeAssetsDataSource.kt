package com.samsaz.thatresumeapp.aboutme

import android.content.res.AssetManager
import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.util.MyJsonParser
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.model.AboutMe
import javax.inject.Inject

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

class AboutMeAssetsDataSource @Inject constructor(
    val jsonParser: MyJsonParser,
    val assets: AssetManager
) : AboutMeDataSource {

    override suspend fun getData(cacheMode: CacheMode): Result<AboutMe> {
        if (cacheMode == CacheMode.Network) {
            throw IllegalArgumentException("AssetDataStore can't provide network liveData")
        }
        val inputStream = assets.open("jsonData/aboutMe.json")
        return jsonParser.parseObject(inputStream, AboutMe::class.java)
    }
}