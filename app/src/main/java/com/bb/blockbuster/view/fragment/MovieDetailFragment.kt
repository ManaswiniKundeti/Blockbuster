package com.bb.blockbuster.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
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
import com.bb.blockbuster.extensions.hide
import com.bb.blockbuster.extensions.show
import com.bb.blockbuster.viewmodel.MovieDetailViewModel
import com.bb.blockbuster.viewstate.Error
import com.bb.blockbuster.viewstate.Loading
import com.bb.blockbuster.viewstate.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val args : MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //let fragment know there is a menu option
        setHasOptionsMenu(true)

        //init inflater for transitions
        val inflater =  TransitionInflater.from(requireContext())

        //enter fragment transition
        enterTransition = inflater.inflateTransition(R.transition.slide_right)

        //exit fragment transition
        exitTransition = inflater.inflateTransition(R.transition.slide_left)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        val cartButton = view.findViewById<Button>(R.id.add_to_cart_button)
        val konfettiView = view.findViewById<KonfettiView>(R.id.detail_fragment_konfetti_view)

        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { viewstate->
            when(viewstate) {
                is Success -> {
                    movie_detail_progress_bar.hide()
                    val movieDetail = viewstate.data
                    movie_detail_image_view.load(movieDetail.movieImageUri.buildImageUri()) {
                        transformations(RoundedCornersTransformation())
                    }
                    // Adding accessibility support
                    movie_detail_image_view.contentDescription = getString(R.string.str_movie_poster_description, movieDetail.movieName)

                    movie_detail_name_text_view.text = movieDetail.movieName
                    movie_detail_rating_bar.rating = movieDetail.movieRating / 2
                    vote_count_text_view.text = getString(R.string.str_vote_count, movieDetail.movieVoteCount.toString())
                    release_date_text_view.text = getString(R.string.str_released_on, movieDetail.movieReleaseDate)
                    overview_text_view.text = movieDetail.movieOverview
                    cartButton.text = getString(R.string.str_add_to_cart, movieDetail.moviePrice)
                }
                is Loading -> {
                    movie_detail_progress_bar.show()
                }
                is Error -> {
                    movie_detail_progress_bar.hide()
                    Toast.makeText(requireContext(), viewstate.errMsg, Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.fetchMovieById(args.movieId)

        cartButton.setOnClickListener {
            viewModel.addToCart(args.movieId)
            Toast.makeText(activity,getString(R.string.str_add_to_cart_toast_message), Toast.LENGTH_LONG).show()

            konfettiView.build()
                .addColors(Color.WHITE, Color.BLUE, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square, Shape.Circle)
                .addSizes(Size(12))
                .setPosition(-50f, konfettiView.width + 50f, -50f, -50f)
                .streamFor(300, 3000L)
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