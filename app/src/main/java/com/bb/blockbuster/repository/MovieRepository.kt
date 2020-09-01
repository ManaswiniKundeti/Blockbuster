package com.bb.blockbuster.repository

import com.bb.blockbuster.model.Cart
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.network.IApiService
import com.bb.blockbuster.persistence.AppDatabase
import javax.inject.Inject

/**
 * Repository that abstracts the different data sources
 *
 * @param apiService: API service for the TMDB movie API
 * @param appDatabase: Local room database to cache movies and user's cart
 */
class MovieRepository @Inject constructor(private val apiService : IApiService, private val appDatabase : AppDatabase) {

    /**
     * Method to fetch movies
     *
     * @param forceRefresh Flag to force refresh from API
     */
    suspend fun fetchMovies(forceRefresh: Boolean): List<Movie> {
        return if (forceRefresh) {
            fetchMoviesFromApiAndCacheLocally()
        } else {
            // First try to fetch movies from database
            val localMovies = appDatabase.movieDao().getMovies()
            return if (localMovies.isNullOrEmpty()) {
                fetchMoviesFromApiAndCacheLocally()
            } else {
                localMovies
            }
        }
    }

    /**
     * Helper method to fetch movies from the TMBD Api Service
     *
     * @return List of Movies
     *
     * @see Movie
     */
    private suspend fun fetchMoviesFromApi(): List<Movie> {
        return try {
            val apiResponse = apiService.fetchMoviesList()
            if (apiResponse.isSuccessful && apiResponse.body() != null) {
                val apiMovies = apiResponse.body()!!.movieList
                apiMovies.forEach { movie ->
                    movie.moviePrice = getRandomPrice()
                }

                apiMovies
            } else {
                mutableListOf()
            }
        } catch (e: Exception) {
            mutableListOf()
        }
    }

    /**
     * Helper method to fetch movies from the API, cache them locally and return cached list of movies
     *
     * @return List of Movies
     *
     * @see Movie
     */
    private suspend fun fetchMoviesFromApiAndCacheLocally(): List<Movie> {
        val apiMovies = fetchMoviesFromApi()

        appDatabase.movieDao().insertMovies(apiMovies)
        return appDatabase.movieDao().getMovies()
    }

    /**
     * Method to fetch movie by id from the local database
     *
     * @param movieId Integer Id of the required movie
     *
     * @return Movie
     *
     * @see Movie
     */
    suspend fun fetchMovieById(movieId : Int) : Movie {
        return appDatabase.movieDao().getMovieById(movieId)
    }

    /**
     * Method to add movie to cart
     *
     * @param movieId Id of the movie to add to cart
     */
    suspend fun addToCart(movieId : Int) {
        appDatabase.cartDao().insertCart(Cart(movieId))
    }

    /**
     * Method to fetch cart of the user
     *
     * @return List of Movies
     *
     * @see Movie
     */
    suspend fun fetchCartDetails() : List<Movie> {
        return appDatabase.cartDao().getCartDetails()
    }

    /**
     * Helper method to generate a random price for a movie
     */
    private fun getRandomPrice() = ((5..9).random() + 0.99).toFloat()

}