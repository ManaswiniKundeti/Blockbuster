package com.bb.blockbuster

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bb.blockbuster.model.Movie
import com.bb.blockbuster.persistence.AppDatabase
import com.bb.blockbuster.persistence.MovieDao
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

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
            val movies = movieDao.getMovies()
            assertTrue("movies is not empty", movies.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_movie_test() {
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
            val movieTwo = Movie(
                11,
                "Avengers",
                "\\/qVygtf2vU15L2yKS4Ke44U4oMdD.jpg",
                "This is an overview",
                6.7f,
                "2020-08-14",
                869,
                8.9f
            )
            movieList.add(movieOne)
            movieList.add(movieTwo)
            movieDao.insertMovies(movieList)
            val moviesFromDb = movieDao.getMovies()
            assertTrue("Movies list not equal to 2", moviesFromDb.size == 2)
        }
    }

    @Test
    @Throws(Exception::class)
    fun get_movie_by_id_test() {
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

            val movieFromDb = movieDao.getMovieById(11)
            assertTrue("Movies id not equal to 11", movieFromDb.movieId == 11)
        }
    }


}