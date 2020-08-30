package com.bb.blockbuster.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Cart (
    @PrimaryKey
    val movieId : Int
)