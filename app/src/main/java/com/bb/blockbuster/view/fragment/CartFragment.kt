package com.bb.blockbuster.view.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bb.blockbuster.R
import com.bb.blockbuster.extensions.hide
import com.bb.blockbuster.extensions.show
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.view.adapter.CartListAdapter
import com.bb.blockbuster.viewmodel.CartListViewModel
import com.bb.blockbuster.viewstate.Error
import com.bb.blockbuster.viewstate.Loading
import com.bb.blockbuster.viewstate.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart.*

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewModel: CartListViewModel by viewModels()

    private val cartMoviesList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enter fragment transition
        val inflater =  TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_cart, container, false)

        val cartTotalTextView : TextView = view.findViewById(R.id.total_price_text_view)

        val cartRecyclerView : RecyclerView = view.findViewById(R.id.cart_recycler_view)
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val cartListAdapter = CartListAdapter(requireContext(), cartMoviesList)
        cartRecyclerView.adapter = cartListAdapter

        viewModel.cartMovieListLiveData.observe(viewLifecycleOwner, Observer { viewstate ->
            when(viewstate){
                is Loading -> {
                    cart_progress_bar.show()
                }
                is Error -> {
                    cart_progress_bar.hide()
                    Toast.makeText(requireContext(), viewstate.errMsg, Toast.LENGTH_SHORT).show()
                }
                is Success -> {
                    cart_progress_bar.hide()
                    cartMoviesList.clear()
                    cartMoviesList.addAll(viewstate.data)

                    if (viewstate.data.isEmpty()) {
                        toggleView(true)
                    } else {
                        var totalPrice = 0f
                        viewstate.data.forEach { movie ->
                            totalPrice += movie.moviePrice
                        }
                        cartTotalTextView.text =  getString(R.string.str_total_price, totalPrice)

                        cartListAdapter.notifyDataSetChanged()
                        toggleView(false)
                    }
                }
            }
        })

        return view
    }

    private fun toggleView(isCartEmpty: Boolean) {
        if (isCartEmpty) {
            cart_recycler_view.hide()
            cart_separator_view.hide()
            total_price_text_view.hide()

            empty_cart_image_view.show()
            empty_cart_text_view.show()
        } else {
            cart_recycler_view.show()
            cart_separator_view.show()
            total_price_text_view.show()

            empty_cart_image_view.hide()
            empty_cart_text_view.hide()
        }
    }
}