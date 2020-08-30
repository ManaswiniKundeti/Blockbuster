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

class CartListViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _cartMovieListLiveData : MutableLiveData<ViewState<List<Movie>>> = MutableLiveData()
    val cartMovieListLiveData : LiveData<ViewState<List<Movie>>> = _cartMovieListLiveData

    init {
        fetchCartMovies()
    }

    fun fetchCartMovies() {
        _cartMovieListLiveData.value = Loading
        viewModelScope.launch {
            val cartMoviesList = movieRepository.fetchCartDetails()
            if (cartMoviesList.isNullOrEmpty()) {
                _cartMovieListLiveData.postValue(Error("Could not load cart movies details"))
            } else {
                _cartMovieListLiveData.postValue(Success(cartMoviesList))
            }
        }
    }
}