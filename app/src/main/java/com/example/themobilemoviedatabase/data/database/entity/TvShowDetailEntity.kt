package com.example.themobilemoviedatabase.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themobilemoviedatabase.domain.model.*

@Entity(tableName = "tv_show_table")
data class TvShowDetailEntity(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val first_air_date: String? = null,
    val homepage: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val in_production: Boolean? = null,
    val last_air_date: String? = null,
    val name: String? = null,
    val number_of_episodes: Int? = null,
    val number_of_seasons: Int? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
)
