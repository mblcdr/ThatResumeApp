package com.samsaz.thatresumeapp.aboutme

import com.samsaz.thatresumeapp.data.BaseCacheRepository
import com.samsaz.thatresumeapp.model.AboutMe
import javax.inject.Inject
import javax.inject.Named

class AboutMeRepository @Inject constructor(
    @Named("remote") override val remoteDataSource: AboutMeDataSource,
    @Named("assets") override val assetsDataSource: AboutMeDataSource
) : BaseCacheRepository<AboutMe>()