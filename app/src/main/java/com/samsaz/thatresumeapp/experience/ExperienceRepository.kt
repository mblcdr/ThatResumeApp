package com.samsaz.thatresumeapp.experience

import com.samsaz.shared.util.NetworkHelper
import com.samsaz.thatresumeapp.data.BaseCacheRepository
import com.samsaz.thatresumeapp.model.Experience
import javax.inject.Inject
import javax.inject.Named

class ExperienceRepository @Inject constructor(
    networkHelper: NetworkHelper,
    @Named("remote") override val remoteDataSource: ExperienceDataSource,
    @Named("assets") override val assetsDataSource: ExperienceDataSource
) : BaseCacheRepository<List<Experience>>(networkHelper)