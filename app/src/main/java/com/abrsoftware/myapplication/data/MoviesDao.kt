package com.abrsoftware.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesDao {
    @Insert
    fun insertMovies(movie: Movie)

    @Query("Select * from movies")
    fun gelAllUsers(): List<Movie>
}