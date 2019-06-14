package com.samsaz.thatresumeapp.aboutme

import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.util.Result
import com.samsaz.shared.util.tryResult
import com.samsaz.thatresumeapp.data.Api
import com.samsaz.thatresumeapp.model.AboutMe
import javax.inject.Inject

class AboutMeRemoteDataSource @Inject constructor(
    private val api: Api
) : AboutMeDataSource {

    override suspend fun getData(cacheMode: CacheMode): Result<AboutMe> =
        tryResult { api.getAboutMe(cacheMode) }

}