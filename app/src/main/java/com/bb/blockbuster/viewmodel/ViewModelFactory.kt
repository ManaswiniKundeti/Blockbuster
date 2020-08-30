package com.bb.blockbuster.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bb.blockbuster.network.createApiService
import com.bb.blockbuster.persistence.AppDatabase
import com.bb.blockbuster.repository.MovieRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context : Context) : ViewModelProvider.Factory {

    private val apiService = createApiService()
    private val appDatabase = AppDatabase.getInstance(context)
    private val movieRepository = MovieRepository(apiService, appDatabase)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesListViewModel::class.java) -> {
                MoviesListViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(CartListViewModel::class.java) -> {
                CartListViewModel(movieRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown view model class")
        }

    }
}