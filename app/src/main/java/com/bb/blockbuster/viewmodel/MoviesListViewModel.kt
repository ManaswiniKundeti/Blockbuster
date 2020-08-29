package com.bb.blockbuster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.repository.MovieRepository
import com.bb.blockbuster.viewstate.Error
import com.bb.blockbuster.viewstate.Loading
import com.bb.blockbuster.viewstate.Success
import com.bb.blockbuster.viewstate.ViewState
import kotlinx.coroutines.launch

class MoviesListViewModel(private val movieRepository :MovieRepository) : ViewModel() {

    private val _movieListLiveData : MutableLiveData<ViewState<List<Movie>>> = MutableLiveData()
    val movieListLiveData : LiveData<ViewState<List<Movie>>> = _movieListLiveData

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        _movieListLiveData.value = Loading
        viewModelScope.launch {
            val movieList = movieRepository.fetchMovies()
            if (movieList.isNullOrEmpty()) {
                _movieListLiveData.postValue(Error("Could not load movies"))
            } else {
                _movieListLiveData.postValue(Success(movieList))
            }
        }
    }

}