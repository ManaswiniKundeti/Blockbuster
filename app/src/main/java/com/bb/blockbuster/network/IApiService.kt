package com.bb.blockbuster.network

import com.bb.blockbuster.BuildConfig
import com.bb.blockbuster.model.MovieResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface IApiService {

    @GET("popular?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun fetchMoviesList() : Response<MovieResult>
}