package com.bb.blockbuster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesListViewModel(private val movieRepository :MovieRepository) : ViewModel() {

    private val _movieListLiveData : MutableLiveData<List<Movie>> = MutableLiveData()
    val movieListLiveData : LiveData<List<Movie>> = _movieListLiveData

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            val movieList = movieRepository.fetchMovies()
            _movieListLiveData.value = movieList
        }
    }
}