package com.bb.blockbuster.repository

import com.bb.blockbuster.model.Cart
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.network.IApiService
import com.bb.blockbuster.persistence.AppDatabase
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService : IApiService, private val appDatabase : AppDatabase) {

    suspend fun fetchMovies():List<Movie> {
        try {
            val apiResponse = apiService.fetchMoviesList()
            return if (apiResponse.isSuccessful && apiResponse.body() != null) {
                val apiMovieList = apiResponse.body()!!.movieList

                apiMovieList.forEach { movie ->
                    movie.moviePrice = getRandomPrice()
                }

                // Inserting in Database
                appDatabase.movieDao().insertMovies(apiMovieList)
                appDatabase.movieDao().getMovies()
            } else {
                mutableListOf()
            }
        } catch (e: Exception) {
            return mutableListOf()
        }
    }

    suspend fun fetchMovieById(movieId : Int) : Movie {
        return appDatabase.movieDao().getMovieById(movieId)
    }

    suspend fun addToCart(movieId : Int) {
        appDatabase.cartDao().insertCart(Cart(movieId))
    }

    suspend fun fetchCartDetails() : List<Movie> {
        return appDatabase.cartDao().getCartDetails()
    }

    private fun getRandomPrice() = ((5..9).random() + 0.99).toFloat()

}