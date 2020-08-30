package com.bb.blockbuster.view.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bb.blockbuster.R
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.view.adapter.MovieListAdapter
import com.bb.blockbuster.viewmodel.MoviesListViewModel
import com.bb.blockbuster.viewmodel.ViewModelFactory
import com.bb.blockbuster.viewstate.Loading
import com.bb.blockbuster.viewstate.Success
import com.bb.blockbuster.viewstate.ViewState

class MoviesListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, MovieListAdapter.IItemClickListener  {

    private val viewmodelFactory by lazy { ViewModelFactory(requireContext()) }
    private val viewModel: MoviesListViewModel by viewModels {
        viewmodelFactory
    }

    private val movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //let fragment know there is a menu option
        setHasOptionsMenu(true)

        //exit fragment transition
        val inflater =  TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_left)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val movieRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.movie_refresh_layout)
        movieRefreshLayout.setOnRefreshListener(this)

        //display and update recycler view
        val movieRecyclerView : RecyclerView = view.findViewById(R.id.movies_recycler_view)
        movieRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val movieListAdapter = MovieListAdapter(requireContext(), movieList, this)
        movieRecyclerView.adapter = movieListAdapter

        viewModel.movieListLiveData.observe(viewLifecycleOwner,
            Observer<ViewState<List<Movie>>> { viewstate ->
                when (viewstate) {
                    is Loading -> {
                        movieRefreshLayout.isRefreshing = true
                    }
                    is Error -> {
                        movieRefreshLayout.isRefreshing = false
                        // TODO : Show error message
                    }
                    is Success -> {
                        movieRefreshLayout.isRefreshing = false
                        if(!viewstate.data.isNullOrEmpty()){
                            movieList.clear()
                            movieList.addAll(viewstate.data)

                            movieListAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })
        return view
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        viewModel.fetchMovies()
    }

    override fun onItemClick(movie: Movie) {
        val navDirection = MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(movie.movieId)
        findNavController().navigate(navDirection)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cart, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.cart_menu_item -> {
                val navDirections = MoviesListFragmentDirections.actionMoviesListFragmentToCartFragment()
                findNavController().navigate(navDirections)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}