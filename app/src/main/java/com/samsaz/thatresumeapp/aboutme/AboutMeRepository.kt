package com.samsaz.thatresumeapp.aboutme

import com.samsaz.thatresumeapp.data.BaseCacheRepository
import com.samsaz.thatresumeapp.model.AboutMe
import com.samsaz.thatresumeapp.util.NetworkHelper
import javax.inject.Inject
import javax.inject.Named

class AboutMeRepository @Inject constructor(
    networkHelper: NetworkHelper,
    @Named("remote") override val remoteDataSource: AboutMeDataSource,
    @Named("assets") override val assetsDataSource: AboutMeDataSource
) : BaseCacheRepository<AboutMe>(networkHelper)