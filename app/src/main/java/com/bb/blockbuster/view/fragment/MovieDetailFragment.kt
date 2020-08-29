package com.bb.blockbuster.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.bb.blockbuster.R
import com.bb.blockbuster.extensions.buildImageUri
import com.bb.blockbuster.viewmodel.MovieDetailViewModel
import com.bb.blockbuster.viewmodel.MoviesListViewModel
import com.bb.blockbuster.viewmodel.ViewModelFactory
import com.bb.blockbuster.viewstate.Error
import com.bb.blockbuster.viewstate.Loading
import com.bb.blockbuster.viewstate.Success
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    val args : MovieDetailFragmentArgs by navArgs()

    private val viewmodelFactory by lazy { ViewModelFactory(requireContext()) }
    private val viewModel: MovieDetailViewModel by viewModels {
        viewmodelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { viewstate->
            when(viewstate) {
                is Success -> {
                    val movieDetail = viewstate.data
                    movie_detail_image_view.load(movieDetail.movieImageUri.buildImageUri()) {
                        transformations(RoundedCornersTransformation())
                    }
                    movie_detail_name_text_view.text = movieDetail.movieName
                    movie_detail_rating_bar.rating = movieDetail.movieRating / 2
                    vote_count_text_view.text = getString(R.string.str_vote_count, movieDetail.movieVoteCount.toString())
                    release_date_text_view.text = getString(R.string.str_released_on, movieDetail.movieReleaseDate)
                    overview_text_view.text = movieDetail.movieOverview
                    add_to_cart_button.text = getString(R.string.str_add_to_cart, "19.99")
                }
                is Loading -> {}
                is Error -> {}
            }
        })
        viewModel.fetchMovieById(args.movieId)
        return view

    }
}