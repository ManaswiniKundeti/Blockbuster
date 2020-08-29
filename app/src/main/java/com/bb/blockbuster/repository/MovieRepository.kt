package com.bb.blockbuster.repository

import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.network.IApiService
import com.bb.blockbuster.persistence.AppDatabase

class MovieRepository(private val apiService : IApiService, private val appDatabase : AppDatabase) {

    suspend fun fetchMovies():List<Movie>{
        val apiResponse = apiService.fetchMoviesList()
        return if (apiResponse.isSuccessful && apiResponse.body() != null) {
            val apiMovieList = apiResponse.body()!!.movieList

            // Inserting in Database
            appDatabase.movieDao().insertMovies(apiMovieList)
            appDatabase.movieDao().getMovies()
        } else {
            mutableListOf()
        }
    }
}