package com.samsaz.thatresumeapp.data

import com.nhaarman.mockitokotlin2.*
import com.samsaz.shared.data.CacheMode
import com.samsaz.shared.data.DataSource
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.util.FakeNetworkHelper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


class BaseCacheRepositoryTest {

    companion object {
        const val AssetsValue = 1
        const val RemoteCacheValue = 2
        const val RemoteValue = 3
    }

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
        return object : BaseCacheRepository<Int>(FakeNetworkHelper()) {
            override val remoteDataSource: DataSource<Int> = remoteDataSource
            override val assetsDataSource: DataSource<Int> = assetsDataSource
        }
    }

    @Test
    fun whenRemoteFailsAssetsLoadNonTheLessTest() {
        val remoteDataSource = createDataSource(error(), error())
        val assetsDataSource = createDataSource(success(AssetsValue), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)
        val listener = mock<DataStateListener<Int>>()

        runBlocking { baseRepository.loadData(listener) }

        verify(listener).onDataChange(1)
    }

    @Test
    fun ifCacheExistsItIsPreferredOverAssetsTest() {
        val remoteDataSource = createDataSource(success(RemoteCacheValue), error())
        val assetsDataSource = createDataSource(success(AssetsValue), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)
        val listener = mock<DataStateListener<Int>>()

        runBlocking { baseRepository.loadData(listener) }

        verify(listener).onDataChange(2)
    }

    @Test
    fun ifRemoteDataExistsItPrevailsWhenCached() {
        val remoteDataSource = createDataSource(success(RemoteCacheValue), success(RemoteValue))
        val assetsDataSource = createDataSource(success(AssetsValue), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)
        val listener = mock<DataStateListener<Int>>()


        runBlocking { baseRepository.loadData(listener) }

        argumentCaptor<Int> {
            verify(listener, times(2)).onDataChange(capture())
            assertEquals(RemoteCacheValue, firstValue)
            assertEquals(RemoteValue, secondValue)
        }
    }

    @Test
    fun ifRemoteDataExistsItPrevailsWhenNotCached() {
        val remoteDataSource = createDataSource(error(), success(RemoteValue))
        val assetsDataSource = createDataSource(success(AssetsValue), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)
        val listener = mock<DataStateListener<Int>>()


        runBlocking { baseRepository.loadData(listener) }

        argumentCaptor<Int> {
            verify(listener, times(2)).onDataChange(capture())
            assertEquals(AssetsValue, firstValue)
            assertEquals(RemoteValue, secondValue)
        }
    }

    @Test
    fun viewStateIsErrorIfRemoteFetchFails() {
        val remoteDataSource = createDataSource(error(), error())
        val assetsDataSource = createDataSource(success(AssetsValue), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)
        val listener = mock<DataStateListener<Int>>()

        runBlocking { baseRepository.loadData(listener) }

        argumentCaptor<ViewLoadingState> {
            verify(listener, times(2)).onStateChange(capture())
            assertTrue(firstValue is ViewLoadingState.Loading)
            assertTrue(secondValue is ViewLoadingState.Error)
        }
    }

    @Test
    fun viewStateIsSuccessIfRemoteFetchedSuccessfully() {
        val remoteDataSource = createDataSource(success(RemoteCacheValue), success(RemoteValue))
        val assetsDataSource = createDataSource(success(AssetsValue), error())
        val baseRepository = createRepository(remoteDataSource, assetsDataSource)
        val listener = mock<DataStateListener<Int>>()

        runBlocking { baseRepository.loadData(listener) }

        argumentCaptor<ViewLoadingState> {
            verify(listener, times(2)).onStateChange(capture())
            assertTrue(firstValue is ViewLoadingState.Loading)
            assertTrue(secondValue is ViewLoadingState.Success)
        }
    }
}