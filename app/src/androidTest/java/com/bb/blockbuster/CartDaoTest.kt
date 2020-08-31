package com.bb.blockbuster

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bb.blockbuster.model.Cart
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.persistence.AppDatabase
import com.bb.blockbuster.persistence.CartDao
import com.bb.blockbuster.persistence.MovieDao
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CartDaoTest {

    private lateinit var movieDao: MovieDao
    private lateinit var cartDao: CartDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        cartDao = db.cartDao()
        movieDao = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun empty_data_base_test() {
        runBlocking {
            val cartList = cartDao.getCartDetails()
            Assert.assertTrue("Cart is not empty", cartList.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_cart_test() {
        runBlocking {
            val movieList = mutableListOf<Movie>()
            val movieOne = Movie(
                12,
                "Avengers",
                "\\/qVygtf2vU15L2yKS4Ke44U4oMdD.jpg",
                "This is an overview",
                6.7f,
                "2020-08-14",
                869,
                8.9f
            )
            movieList.add(movieOne)
            movieDao.insertMovies(movieList)

            val cartOne = Cart(12)
            cartDao.insertCart(cartOne)
            val cartsFromDb = cartDao.getCartDetails()
            Assert.assertTrue("Cart is not inserted", cartsFromDb.size == 1)
        }
    }

}