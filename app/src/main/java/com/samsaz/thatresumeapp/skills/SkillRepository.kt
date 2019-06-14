package com.samsaz.thatresumeapp.skills

import com.samsaz.thatresumeapp.data.BaseCacheRepository
import com.samsaz.thatresumeapp.model.Skill
import javax.inject.Inject
import javax.inject.Named

class SkillRepository @Inject constructor(
    @Named("remote") override val remoteDataSource: SkillDataSource,
    @Named("assets") override val assetsDataSource: SkillDataSource
) : BaseCacheRepository<List<Skill>>()