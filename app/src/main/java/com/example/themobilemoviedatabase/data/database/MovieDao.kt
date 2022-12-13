package com.example.themobilemoviedatabase.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themobilemoviedatabase.data.database.entity.MovieDetailEntity
import com.example.themobilemoviedatabase.data.database.entity.TvShowDetailEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getSavedMovie(): List<MovieDetailEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM movie_table WHERE id = :id)")
    fun checkSavedMovieById(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetail: MovieDetailEntity): Long

    @Query("DELETE FROM movie_table WHERE id = :id")
    fun deleteMovieDetail(id: Int)

    @Query("SELECT * FROM tv_show_table")
    fun getSavedTvShow(): List<TvShowDetailEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM tv_show_table WHERE id = :id)")
    fun checkSavedTvShowById(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowDetail(tvShowDetailEntity: TvShowDetailEntity): Long

    @Query("DELETE FROM tv_show_table WHERE id = :id")
    fun deleteTvShowDetail(id: Int)
}