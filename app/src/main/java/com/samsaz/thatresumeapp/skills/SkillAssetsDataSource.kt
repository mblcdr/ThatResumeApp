package com.samsaz.thatresumeapp.skills

import android.content.res.AssetManager
import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.util.MyJsonParser
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.model.Skill
import javax.inject.Inject

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

class SkillAssetsDataSource @Inject constructor(
    val jsonParser: MyJsonParser,
    val assets: AssetManager
) : SkillDataSource {

    override suspend fun getData(cacheMode: CacheMode): Result<List<Skill>> {
        if (cacheMode == CacheMode.Network) {
            throw IllegalArgumentException("AssetDataStore can't provide network data")
        }
        val inputStream = assets.open("jsonData/skills.json")
        return jsonParser.parseList(inputStream, Skill::class.java)
    }

}