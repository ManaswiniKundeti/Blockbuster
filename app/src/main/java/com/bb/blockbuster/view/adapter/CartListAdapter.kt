package com.bb.blockbuster.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.bb.blockbuster.R
import com.bb.blockbuster.extensions.buildImageUri
import com.bb.blockbuster.model.Movie

class CartListAdapter(private val context : Context,
                      private val cartMovieList : List<Movie>) : RecyclerView.Adapter<CartListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        return CartListItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cartMovieList.size
    }

    override fun onBindViewHolder(holder: CartListItemViewHolder, position: Int) {
        holder.movieImageView.load(cartMovieList[position].movieImageUri.buildImageUri()) {
            transformations(RoundedCornersTransformation())
        }
        holder.movieNameTextView.text = cartMovieList[position].movieName
        holder.movieRatingBar.rating = cartMovieList[position].movieRating/2
        holder.moviePriceTextView.text = "$19.99"
    }
}
class CartListItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val movieImageView : ImageView = itemView.findViewById(R.id.cart_movie_image_view)
    val movieNameTextView : TextView = itemView.findViewById(R.id.cart_movie_name_text_view)
    val movieRatingBar : RatingBar = itemView.findViewById(R.id.cart_rating_bar)
    val moviePriceTextView : TextView = itemView.findViewById(R.id.cart_item_price_text_view)
}