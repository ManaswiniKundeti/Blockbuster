package com.bb.blockbuster.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bb.blockbuster.model.Cart
import com.bb.blockbuster.model.Movie

@Database(entities = [Movie::class, Cart::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun cartDao() : CartDao
}