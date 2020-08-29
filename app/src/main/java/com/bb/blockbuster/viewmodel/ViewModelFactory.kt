package com.bb.blockbuster.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bb.blockbuster.network.createTmdbService
import com.bb.blockbuster.repository.MovieRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context : Context) : ViewModelProvider.Factory {

    private val apiService = createTmdbService()
    private val movieRepository = MovieRepository(apiService)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesListViewModel::class.java)){
            return MoviesListViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}