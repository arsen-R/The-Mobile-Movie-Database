package com.example.themobilemoviedatabase.data.network

import com.example.themobilemoviedatabase.data.network.dto.*
import com.example.themobilemoviedatabase.domain.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : MovieResultDto

    @GET("/3/movie/top_rated")
    suspend fun getTrendingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : MovieResultDto

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : MovieResultDto

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : MovieResultDto

    @GET("/3/tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : TvShowResultDto

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : TvShowResultDto

    @GET("/3/tv/on_the_air")
    suspend fun getOnTheAirTv(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : TvShowResultDto

    @GET("/3/tv/airing_today")
    suspend fun getAiringTodayTv(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ) : TvShowResultDto


    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetailById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("append_to_response") appendToResponse: String = "credits,images,similar"
    ) : MovieDetailsDto

    @GET("/3/tv/{tv_show_id}")
    suspend fun getTvShowDetailById(
        @Path("tv_show_id") tvShowId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("append_to_response") appendToResponse: String = "credits,images,similar"
    ) : TvShowDetailDto

    @GET("/3/tv/{tv_show_id}/season/{season_number}")
    suspend fun getTvShowSeasonDetailById(
        @Path("tv_show_id") tvShowId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ) : TvShowSeasonDetailDto

    @GET("/3/tv/{tv_show_id}/season/{season_number}/episode/{episode_number}")
    suspend fun getTvEpisodeDetailById(
        @Path("tv_show_id") tvShowId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        //@Query("append_to_response") appendToResponse: String = "credits"
    ) : TvEpisodeDetailsDto
}