package com.bb.blockbuster.repository

import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.network.ITmdbService

class MovieRepository( private val apiService : ITmdbService) {

    suspend fun fetchMovies():List<Movie>{
        val response = apiService.fetchMoviesList()
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.mMovieList
        } else {
            mutableListOf()
        }
    }
}