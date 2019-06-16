package com.samsaz.thatresumeapp.skills

import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.thatresumeapp.data.BaseCacheRepository
import com.samsaz.thatresumeapp.model.Skill
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class SkillRepository @Inject constructor(
    @Named("remote") override val remoteDataSource: SkillDataSource,
    @Named("assets") override val assetsDataSource: SkillDataSource
) : BaseCacheRepository<List<Skill>>() {

    suspend fun filterData(data: List<Skill>, dispatchers: CoroutineDispatchers, filter: String?):
            List<Skill> {
        if (filter == null || filter.length < 2) {
            return data
        }

        return withContext(dispatchers.io) {
            val level3List = mutableListOf<Skill>()
            val level2List = mutableListOf<Skill>()
            val level1List = mutableListOf<Skill>()

            for (skill in data) {
                when {
                    skill.name.startsWith(filter) -> level3List.add(skill)
                    skill.name.contains(filter) -> level2List.add(skill)
                    skill.description.contains(filter) -> level1List.add(skill)
                }
            }
            level3List.addAll(level2List)
            level3List.addAll(level1List)
            level3List
        }
    }
}