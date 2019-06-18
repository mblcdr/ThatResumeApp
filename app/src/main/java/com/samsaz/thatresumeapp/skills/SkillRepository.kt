package com.samsaz.thatresumeapp.skills

import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.thatresumeapp.data.BaseCacheRepository
import com.samsaz.thatresumeapp.model.Skill
import com.samsaz.thatresumeapp.util.NetworkHelper
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class SkillRepository @Inject constructor(
    networkHelper: NetworkHelper,
    @Named("remote") override val remoteDataSource: SkillDataSource,
    @Named("assets") override val assetsDataSource: SkillDataSource
) : BaseCacheRepository<List<Skill>>(networkHelper) {

    suspend fun filterData(data: List<Skill>, dispatchers: CoroutineDispatchers, filter: String?):
            List<Skill> {
        if (filter == null || filter.length < 2) {
            return data
        }

        return withContext(dispatchers.io) {
            val level3List = mutableListOf<Skill>()
            val level2List = mutableListOf<Skill>()
            val level1List = mutableListOf<Skill>()
            val f = filter.toLowerCase(Locale.US)
            for (skill in data) {
                when {
                    skill.name.toLowerCase(Locale.US).startsWith(f) -> level3List.add(skill)
                    skill.name.toLowerCase(Locale.US).contains(f) -> level2List.add(skill)
                    skill.description.toLowerCase(Locale.US).contains(f) -> level1List.add(skill)
                }
            }
            level3List.addAll(level2List)
            level3List.addAll(level1List)
            level3List
        }
    }
}