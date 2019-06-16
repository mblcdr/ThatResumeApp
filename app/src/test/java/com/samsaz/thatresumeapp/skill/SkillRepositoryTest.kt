package com.samsaz.thatresumeapp.skill

import com.nhaarman.mockitokotlin2.mock
import com.samsaz.thatresumeapp.model.Skill
import com.samsaz.thatresumeapp.skills.SkillRepository
import com.samsaz.thatresumeapp.util.provideFakeCoroutineDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SkillRepositoryTest {

    private val repo: SkillRepository = SkillRepository(mock(), mock())
    private val testList = mutableListOf<Skill>().apply {
        for (i in 0..9) {
            add(Skill("Skill-$i", null, "Desc-$i", null))
        }
    }

    private fun runFilter(skills: List<Skill> = testList, filter: String?): List<Skill> {
        return runBlocking {
            repo.filterData(skills, provideFakeCoroutineDispatchers(), filter)
        }
    }

    @Test
    fun nullFilterTest() {
        val result = runFilter(filter = null)

        assertEquals(testList.size, result.size)
        assertEquals(testList, result)
    }

    @Test
    fun shortFilterTest() {
        val result1 = runFilter(filter = "")
        val result2 = runFilter(filter = "1")

        assertEquals(testList.size, result1.size)
        assertEquals(testList, result1)
        assertEquals(testList.size, result2.size)
        assertEquals(testList, result2)
    }

    @Test
    fun singleResultFilterTest() {
        val result = runFilter(filter = testList[0].name)
        assertEquals(1, result.size)
        assertEquals(testList[0], result[0])
    }

    @Test
    fun noResultFilterTest() {
        val result = runFilter(filter = "Rafting")

        assertEquals(0, result.size)
    }

    /** Scoring:
     * name.startsWith = 3
     * name.contains = 2
     * description.contains = 1 **/
    @Test
    fun correctOrderFilterTest() {
        val level3Match = Skill("Rxjava", null, "", null)
        val level2Match = Skill("MvRx", null, "", null)
        val level1Match = Skill("Java", null, "RxJava", null)
        val noMatch = Skill("Kotlin", null, "Ktx", null)

        val list = listOf(level2Match, level3Match, noMatch, level1Match)
        val correctFilteredList = listOf(level3Match, level2Match, level1Match)

        val result = runFilter(list, "Rx")

        assertEquals(correctFilteredList, result)
    }

    @Test
    fun caseInsensitiveFilterTest() {
        val result = runFilter(filter = "skiLL-0")
        val matchedItem = testList[0]

        assertEquals(1, result.size)
        assertEquals(matchedItem, result[0])
    }
}