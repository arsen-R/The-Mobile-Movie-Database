package com.example.themobilemoviedatabase.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themobilemoviedatabase.data.database.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getSavedMovie(): List<MovieDetailEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM movie_table WHERE id = :id)")
    fun getSavedMovieById(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetail: MovieDetailEntity): Long

    @Query("DELETE FROM movie_table WHERE id = :id")
    fun deleteArticle(id: Int)
}