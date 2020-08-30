package com.bb.blockbuster.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bb.blockbuster.model.Cart
import com.bb.blockbuster.model.Movie

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCart(cart: Cart)

    @Query("SELECT * FROM Movie INNER JOIN Cart ON Cart.movieId = Movie.movieId")
    suspend fun getCartDetails(): List<Movie>
}
