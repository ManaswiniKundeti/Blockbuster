package com.bb.blockbuster.network

import com.bb.blockbuster.BuildConfig
import com.bb.blockbuster.model.MovieResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

fun createTmdbService() : IApiService{

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

interface IApiService {

    @GET("popular?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun fetchMoviesList() : Response<MovieResult>

}