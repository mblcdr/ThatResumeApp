package com.samsaz.thatresumeapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.data.DataSource
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.util.testGetValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class BaseCacheRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private fun createDataSource(
        cacheResult: Result<Int>,
        networkResult: Result<Int>
    ): DataSource<Int> {
        return mock {
            on { runBlocking { getData(CacheMode.Cache) } }.doReturn(cacheResult)
            on { runBlocking { getData(CacheMode.Network) } }.doReturn(networkResult)
        }
    }

    private fun success(result: Int): Result<Int> = Result.Success(result)

    private fun error(): Result<Int> = Result.Error("Error")

    private fun createRepository(
        remoteDataSource: DataSource<Int>,
        assetsDataSource: DataSource<Int>
    ):
            BaseCacheRepository<Int> {
        return object : BaseCacheRepository<Int>() {
            override val remoteDataSource: DataSource<Int> = remoteDataSource
            override val assetsDataSource: DataSource<Int> = assetsDataSource
        }
    }

    @Test
    fun whenRemoteFailsAssetsLoadNonTheLessTest() {
        val remoteDataSource = createDataSource(error(), error())
        val assetsDataSource = createDataSource(success(1), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)

        val dataLiveData = MutableLiveData<Int>()
        val loadingLiveData = MutableLiveData<ViewLoadingState>()

        runBlocking { baseRepository.loadData(dataLiveData, loadingLiveData) }

        assertEquals(1, dataLiveData.testGetValue())
        assertTrue(loadingLiveData.testGetValue() is ViewLoadingState.Error)
    }

    @Test
    fun ifCacheExistsItIsPreferredOverAssetsTest() {
        val remoteDataSource = createDataSource(success(2), error())
        val assetsDataSource = createDataSource(success(1), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)

        val dataLiveData = MutableLiveData<Int>()
        val loadingLiveData = MutableLiveData<ViewLoadingState>()

        runBlocking { baseRepository.loadData(dataLiveData, loadingLiveData) }

        assertEquals(2, dataLiveData.testGetValue())
    }

    @Test
    fun ifRemoteDataExistsItPrevails() {
        val remoteDataSource = createDataSource(success(2), success(3))
        val assetsDataSource = createDataSource(success(1), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)

        val dataLiveData = MutableLiveData<Int>()
        val loadingLiveData = MutableLiveData<ViewLoadingState>()

        runBlocking { baseRepository.loadData(dataLiveData, loadingLiveData) }

        assertEquals(3, dataLiveData.testGetValue())
        assertTrue(loadingLiveData.testGetValue() is ViewLoadingState.Success)
    }
}