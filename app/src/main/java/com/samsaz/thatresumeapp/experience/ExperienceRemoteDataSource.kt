package com.samsaz.thatresumeapp.experience

import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.util.Result
import com.samsaz.shared.util.tryResult
import com.samsaz.thatresumeapp.data.Api
import com.samsaz.thatresumeapp.model.Experience
import javax.inject.Inject

class ExperienceRemoteDataSource @Inject constructor(
    private val api: Api
) : ExperienceDataSource {

    override suspend fun getData(cacheMode: CacheMode): Result<List<Experience>> =
        tryResult { api.getExperiences(cacheMode) }

}