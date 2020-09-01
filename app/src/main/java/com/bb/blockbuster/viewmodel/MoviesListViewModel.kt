package com.bb.blockbuster.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
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

/**
 * View model that supports the MoviesListFragment
 *
 * @param movieRepository: Movie Repository
 * @see MovieRepository
 *
 */
class MoviesListViewModel @ViewModelInject constructor(private val movieRepository : MovieRepository) : ViewModel() {

    private val _movieListLiveData : MutableLiveData<ViewState<List<Movie>>> = MutableLiveData()
    val movieListLiveData : LiveData<ViewState<List<Movie>>> = _movieListLiveData

    init {
        fetchMovies()
    }

    /**
     * Method to fetch list of movies
     *
     * @param forceRefresh : Boolean
     */
    fun fetchMovies(forceRefresh: Boolean = false) {
        _movieListLiveData.value = Loading
        viewModelScope.launch {
            val movieList = movieRepository.fetchMovies(forceRefresh)
            if (movieList.isNullOrEmpty()) {
                _movieListLiveData.postValue(Error("Could not load movies"))
            } else {
                _movieListLiveData.postValue(Success(movieList))
            }
        }
    }

}