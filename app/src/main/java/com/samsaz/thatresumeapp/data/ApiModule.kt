package com.samsaz.thatresumeapp.data

import android.content.Context
import androidx.annotation.Nullable
import com.samsaz.shared.data.CacheMode
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideOkHttpClient(
        @Nullable cache: Cache?,
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .cache(cache)
        interceptors.forEach { builder.addInterceptor(it) }
        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .baseUrl("https://thatresumeapp.samsaz.com/api/v1/")
            .build()
    }

    @Provides
    fun provideRetrofitConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideCache(context: Context) = Cache(context.cacheDir, 20 * 1024 * 1024)

    @Provides
    @IntoSet
    fun provideCachingInterceptor(): Interceptor {
        return Interceptor {
            var request = it.request()
            val cacheMode = request.tag(CacheMode::class.java)

            request = if (cacheMode != null && cacheMode == CacheMode.Cache) {
                request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
            } else {
                request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build()
            }

            val originalResponse = it.proceed(request)
            originalResponse.newBuilder()
                .header("Cache-Control", "max-age=${Integer.MAX_VALUE}")
                .build()
        }
    }
}