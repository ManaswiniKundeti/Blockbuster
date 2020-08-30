package com.bb.blockbuster.di

import com.bb.blockbuster.network.IApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun createApiService() : IApiService {

        val okHttpClient = OkHttpClient
            .Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(IApiService::class.java)
    }
}