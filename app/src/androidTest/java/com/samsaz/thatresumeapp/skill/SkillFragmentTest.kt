package com.samsaz.thatresumeapp.skill

import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.R
import com.samsaz.thatresumeapp.model.Skill
import com.samsaz.thatresumeapp.skills.SkillDataSource
import com.samsaz.thatresumeapp.skills.SkillFragment
import com.samsaz.thatresumeapp.skills.SkillRepository
import com.samsaz.thatresumeapp.skills.SkillViewModel
import com.samsaz.thatresumeapp.test.FragmentTestActivity
import com.samsaz.thatresumeapp.util.FakeNetworkHelper
import com.samsaz.thatresumeapp.util.atPosition
import com.samsaz.thatresumeapp.util.matchesVisible
import com.samsaz.thatresumeapp.util.provideNoDelayCoroutineDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

/**
 * Copyright 2019 Oddrun co.
 * Created and maintained by Hamid Moazzami
 */

class SkillFragmentTest {
    val fragment = SkillFragment()

    val remoteDataSource = mock<SkillDataSource>()
    val assetsDataSource = mock<SkillDataSource>()
    private val testList = mutableListOf<Skill>().apply {
        for (i in 0..9) {
            add(Skill("Skill-$i", null, "Desc-$i", null))
        }
    }

    @get:Rule
    val activityRule = object: ActivityTestRule<FragmentTestActivity>(
        FragmentTestActivity::class.java) {
        override fun afterActivityLaunched() = runOnUiThread {
            activity.startFragment(fragment, this@SkillFragmentTest::inject)
        }
    }

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @Suppress("UNCHECKED_CAST")
    fun inject(fragment: SkillFragment) {
        fragment.viewModelFactory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = SkillRepository(FakeNetworkHelper(), remoteDataSource, assetsDataSource)
                return SkillViewModel(provideNoDelayCoroutineDispatchers(), repo) as T
            }
        }
    }

    @Test
    fun errorStateTest() {
        runBlocking { whenever(remoteDataSource.getData(any())) doReturn Result.Error("") }
        onView(withText("Connection Error")).check(matchesVisible())
    }

    @Test
    fun emptyNoticeTest() {
        runBlocking {
            whenever(remoteDataSource.getData(any())) doReturn Result.Success(emptyList())
        }
        onView(withText(R.string.noItemFound)).check(matchesVisible())
    }

    @Test
    fun searchWorksTest() {
        runBlocking {
            whenever(remoteDataSource.getData(any())) doReturn Result.Success(testList)
        }
        onView(withId(R.id.item_search)).perform(click())

        val vi = onView(isAssignableFrom(AppCompatAutoCompleteTextView::class.java))

        vi.check(matchesVisible())

        vi.perform(typeText("Skill-2"))

        onView(withId(R.id.rvList)).check(matches(atPosition(0,
            hasDescendant(withText("Skill-2")))))
    }

    @Test
    fun searchResetWorksTest() {
        runBlocking {
            whenever(remoteDataSource.getData(any())) doReturn Result.Success(testList)
        }
        onView(withId(R.id.item_search)).perform(click())
        onView(isAssignableFrom(AppCompatAutoCompleteTextView::class.java))
            .perform(typeText("Skill-2"))

        onView(withId(R.id.rvList)).check(matches(atPosition(0,
            hasDescendant(withText("Skill-2")))))

        onView(withId(androidx.appcompat.R.id.search_close_btn)).perform(click())

        onView(withId(R.id.rvList)).check(matches(atPosition(0,
            hasDescendant(withText("Skill-0")))))
    }
}