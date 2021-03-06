package com.bb.blockbuster.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.repository.MovieRepository
import com.bb.blockbuster.viewstate.Loading
import com.bb.blockbuster.viewstate.Success
import com.bb.blockbuster.viewstate.ViewState
import kotlinx.coroutines.launch

/**
 * View model that supports the MovieDetailFragment
 *
 * @param movieRepository: Movie Repository
 * @see MovieRepository
 *
 */
class MovieDetailViewModel @ViewModelInject constructor(private val movieRepository : MovieRepository) : ViewModel() {

    private val _movieLiveData : MutableLiveData<ViewState<Movie>> = MutableLiveData()
    val movieLiveData : LiveData<ViewState<Movie>> = _movieLiveData

    /**
     * Method to fetch movie based in Id
     *
     * @param movieId : Int
     */
    fun fetchMovieById(movieId : Int) {
        if (_movieLiveData.value != null && (_movieLiveData.value is Success || _movieLiveData.value is Loading)) {
            return
        }

        _movieLiveData.value = Loading
        viewModelScope.launch {
            val movie = movieRepository.fetchMovieById(movieId)
            _movieLiveData.postValue(Success(movie))
        }
    }

    /**
     * Method to add movieId to the cart
     *
     * @param movieId : Int
     */
    fun addToCart(movieId : Int) {
        viewModelScope.launch {
            movieRepository.addToCart(movieId)
        }
    }

}