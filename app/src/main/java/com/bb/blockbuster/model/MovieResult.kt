package com.bb.blockbuster.model

import androidx.room.Entity
import com.squareup.moshi.Json

data class MovieResult(
    @field:Json(name = "results") val mMovieList : List<Movie>
)