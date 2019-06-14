package com.samsaz.thatresumeapp.skills

import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.util.Result
import com.samsaz.shared.util.tryResult
import com.samsaz.thatresumeapp.data.Api
import com.samsaz.thatresumeapp.model.Skill
import javax.inject.Inject

class SkillRemoteDataSource @Inject constructor(
    private val api: Api
) : SkillDataSource {

    override suspend fun getData(cacheMode: CacheMode): Result<List<Skill>> =
        tryResult { api.getSkills(cacheMode) }

}