package com.bb.blockbuster.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bb.blockbuster.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movieList : List<Movie>)

    @Query("SELECT * FROM Movie WHERE movieId = :movieId")
    suspend fun getMovieById(movieId : Int) : Movie

    @Query("SELECT * FROM Movie")
    suspend fun getMovies() : List<Movie>
}