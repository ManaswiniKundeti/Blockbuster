package com.bb.blockbuster.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Movie (
    @field:Json(name = "title") @PrimaryKey val mMovieName: String,
    @field:Json(name = "poster_path") val mMovieImageUri : String,
    @field:Json(name = "id") val mMovieId : String,
    @field:Json(name = "overview") val mMovieOverview : String,
    @field:Json(name = "vote_average") val mMovieRating : String,
    @field:Json(name = "release_date") val mMovieReleaseDate : String
)
