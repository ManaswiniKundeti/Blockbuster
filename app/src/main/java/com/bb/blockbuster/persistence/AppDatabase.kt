package com.bb.blockbuster.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bb.blockbuster.model.Cart
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.model.MovieResult

@Database(entities = [Movie::class, Cart::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun cartDao() : CartDao
}