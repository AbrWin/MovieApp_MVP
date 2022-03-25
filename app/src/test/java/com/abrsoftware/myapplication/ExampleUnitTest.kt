package com.abrsoftware.myapplication

import android.os.Parcel
import com.abrsoftware.myapplication.data.Movie
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    lateinit var movie: Movie
    @Before
    fun setUp(){
        movie = Movie(Parcel.obtain())
        movie.movieId = 1234
        movie.title = "Good day"
        movie.type = "upcomming"
        movie.poster_path = "poster.jpg"
        movie.overview = "Good movie"
        movie.release_date = "11/02/2022"

    }
    @Test
    fun checkTypeOne() {
        assertEquals(movie.type, "popular")
    }

    @Test
    fun checkTypeTwo() {
        assertEquals(movie.type, "upcomming")
    }

    fun checkTypeThree() {
        assertEquals(movie.type, "lastest")
    }
}