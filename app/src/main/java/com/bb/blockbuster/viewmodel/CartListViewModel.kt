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
 * View model that supports the CartFragment
 *
 * @param movieRepository: Movie Repository
 * @see MovieRepository
 *
 */
class CartListViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private val _cartMovieListLiveData : MutableLiveData<ViewState<List<Movie>>> = MutableLiveData()
    val cartMovieListLiveData : LiveData<ViewState<List<Movie>>> = _cartMovieListLiveData

    init {
        fetchCartMovies()
    }

    /**
     * Method to fetch movies from the cart
     *
     */
    private fun fetchCartMovies() {
        _cartMovieListLiveData.value = Loading
        viewModelScope.launch {
            val cartMoviesList = movieRepository.fetchCartDetails()
            _cartMovieListLiveData.postValue(Success(cartMoviesList))
        }
    }
}