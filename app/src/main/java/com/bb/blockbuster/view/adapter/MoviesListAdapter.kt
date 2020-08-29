package com.bb.blockbuster.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.bb.blockbuster.R
import com.bb.blockbuster.extensions.buildImageUri
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.view.fragment.MovieDetailFragment
import com.bb.blockbuster.view.fragment.MoviesListFragment
import com.bb.blockbuster.view.fragment.MoviesListFragmentDirections

class MovieListAdapter(private val context : Context,
                       private val movieList : List<Movie>,
                        private val itemClickListener: IItemClickListener) : RecyclerView.Adapter<MovieListItemViewHolder>() {

    interface IItemClickListener {
        fun onItemClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return MovieListItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {
        holder.movieImageView.load(movieList[position].movieImageUri.buildImageUri()) {
            transformations(RoundedCornersTransformation())
        }
        holder.movieNameTextView.text = movieList[position].movieName
        holder.moviePriceTextView.text = "$19.99"

        holder.itemView.setOnClickListener {
             itemClickListener.onItemClick(movieList[position])
        }
    }
}


class MovieListItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val movieImageView : ImageView = itemView.findViewById(R.id.movie_item_image_view)
    val movieNameTextView : TextView = itemView.findViewById(R.id.movie_name_text_view)
    val moviePriceTextView : TextView = itemView.findViewById(R.id.rent_price_text_view)
}