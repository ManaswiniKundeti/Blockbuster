package com.bb.blockbuster.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        var cart_button = view.findViewById<Button>(R.id.add_to_cart_button)

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
                    cart_button.text = getString(R.string.str_add_to_cart, "19.99")
                }
                is Loading -> {}
                is Error -> {}
            }
        })
        viewModel.fetchMovieById(args.movieId)

        cart_button.setOnClickListener {
            viewModel.addToCart(args.movieId)
            Toast.makeText(activity,"Added to Cart successfully", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cart, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.cart_menu_item -> {
                val navDirections = MovieDetailFragmentDirections.actionMovieDetailFragmentToCartFragment2()
                findNavController().navigate(navDirections)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}