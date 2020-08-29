package com.bb.blockbuster.network

import com.bb.blockbuster.BuildConfig
import com.bb.blockbuster.model.MovieResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun createTmdbService() : ITmdbService{

    val okHttpClient = OkHttpClient
        .Builder()
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(ITmdbService::class.java)
}

interface ITmdbService {

    @GET("popular?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun fetchMoviesList() : Response<MovieResult>

//    @GET(
//    fun fetchMovieDetails(
//        @Path("movieId") movieId : String) : retrofit2.Response<Movie>
//    )
}