package com.samsaz.shared.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.withContext
import okio.buffer
import okio.source
import java.io.InputStream
import javax.inject.Inject

class MyJsonParser @Inject constructor(
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatchers
) {

    suspend fun <T> parseObject(inputStream: InputStream, clazz: Class<T>): Result<T> =
        parse(inputStream, getAdapter(clazz))


    suspend fun <T> parseList(inputStream: InputStream, clazz: Class<T>): Result<List<T>> =
        parse(inputStream, getListAdapter(clazz))

    private suspend fun <T> parse(inputStream: InputStream, adapter: JsonAdapter<T>): Result<T> {
        return withContext(dispatcher.io) {
            try {
                val buffer = inputStream.source().buffer()
                val obj = adapter.fromJson(buffer)
                if (obj != null) {
                    Result.Success(obj)
                } else {
                    Result.Error("Parse error")
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                Result.Error(throwable.message ?: "")
            }
        }
    }

    private fun <T> getAdapter(clazz: Class<T>): JsonAdapter<T> {
        return moshi.adapter(clazz)
    }

    private fun <T> getListAdapter(clazz: Class<T>): JsonAdapter<List<T>> {
        val typeToken = Types.newParameterizedType(List::class.java, clazz)
        return moshi.adapter(typeToken)
    }
}