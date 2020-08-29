package com.bb.blockbuster.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Movie (
    @field:Json(name = "id") @PrimaryKey val movieId : Int,
    @field:Json(name = "title") val movieName: String,
    @field:Json(name = "poster_path") val movieImageUri : String,
    @field:Json(name = "overview") val movieOverview : String,
    @field:Json(name = "vote_average") val movieRating : String,
    @field:Json(name = "release_date") val movieReleaseDate : String
)
