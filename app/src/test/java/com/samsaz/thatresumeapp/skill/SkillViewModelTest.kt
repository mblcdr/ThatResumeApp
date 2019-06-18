package com.samsaz.thatresumeapp.skill

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.model.Skill
import com.samsaz.thatresumeapp.skills.SkillDataSource
import com.samsaz.thatresumeapp.skills.SkillRepository
import com.samsaz.thatresumeapp.skills.SkillViewModel
import com.samsaz.thatresumeapp.util.provideFakeCoroutineDispatchers
import com.samsaz.thatresumeapp.util.provideFakeNetworkHelper
import com.samsaz.thatresumeapp.util.provideNoDelayCoroutineDispatchers
import com.samsaz.thatresumeapp.util.testGetValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SkillViewModelTest {

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    private val testList = mutableListOf<Skill>().apply {
        for (i in 0..9) {
            add(Skill("Skill-$i", null, "Desc-$i", null))
        }
    }

    private fun createViewModel(delay: Boolean = false): SkillViewModel {
        val dispatchers: CoroutineDispatchers = if (delay) {
            provideFakeCoroutineDispatchers()
        } else {
            provideNoDelayCoroutineDispatchers()
        }

        val dataSource = object : SkillDataSource {
            override suspend fun getData(cacheMode: CacheMode): Result<List<Skill>> {
                return Result.Success(testList)
            }
        }

        val repo = SkillRepository(provideFakeNetworkHelper(), dataSource, dataSource)

        return SkillViewModel(dispatchers, repo)
    }

    @Test
    fun liveDataIsFilledTest() {
        val vm = createViewModel()

        assertEquals(testList, vm.liveData.testGetValue())
        assertTrue(vm.loadingLiveData.testGetValue() is ViewLoadingState.Success)
    }

    @Test
    fun filteringWorksTest() {
        val vm = createViewModel()

        vm.onSearchQuery("Skill-0")

        assertEquals(vm.liveData.testGetValue(), listOf(testList[0]))
    }

    @Test
    fun twoFiltersBackToBackTest() {
        val vm = createViewModel()

        vm.onSearchQuery("Skill-0")
        vm.onSearchQuery("Skill-1")

        assertEquals(vm.liveData.testGetValue(), listOf(testList[1]))
    }

    @Test
    fun filterResetTest() {
        val vm = createViewModel()

        vm.onSearchQuery("Skill-0")
        vm.onSearchQuery(null)

        assertEquals(vm.liveData.testGetValue(), testList)
    }

    @Test
    fun filterWorksAfterResetTest() {
        val vm = createViewModel()

        vm.onSearchQuery("Skill-0")
        vm.onSearchQuery(null)
        vm.onSearchQuery("Skill-1")

        assertEquals(vm.liveData.testGetValue(), listOf(testList[1]))
    }

    @Test
    fun emptyFilterResultTest() {
        val vm = createViewModel()

        vm.onSearchQuery("Surfing")

        assertEquals(0, vm.liveData.testGetValue()?.size)
    }

    @Test
    fun filterDebounceWorksSingleQuery() {
        val vm = createViewModel(delay = true)

        vm.onSearchQuery("Skill-0")
        Thread.sleep(SkillViewModel.DEBOUNCE_TIME / 2)

        assertEquals(testList, vm.liveData.testGetValue())

        Thread.sleep(SkillViewModel.DEBOUNCE_TIME / 3 * 2)

        assertEquals(listOf(testList[0]), vm.liveData.testGetValue())
    }

    @Test
    fun filterDebounceWorksMultipleQuery() {
        val vm = createViewModel(delay = true)

        vm.onSearchQuery("Skill-0")
        Thread.sleep(SkillViewModel.DEBOUNCE_TIME / 2)
        vm.onSearchQuery("Skill-1")
        Thread.sleep(SkillViewModel.DEBOUNCE_TIME / 3 * 2)

        assertEquals(testList, vm.liveData.testGetValue())

        Thread.sleep(SkillViewModel.DEBOUNCE_TIME)

        assertEquals(listOf(testList[1]), vm.liveData.testGetValue())
    }
}