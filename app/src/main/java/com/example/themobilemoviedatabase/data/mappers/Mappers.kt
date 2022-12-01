package com.example.themobilemoviedatabase.data.mappers

import com.example.themobilemoviedatabase.data.network.dto.*
import com.example.themobilemoviedatabase.domain.model.*

fun MovieDto.toMovie(): Movie {
    return Movie(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        genre_ids = this.genre_ids,
        id = this.id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        mediaType = this.mediaType
    )
}

fun TvShowDto.toTvShow(): TvShow {
    return TvShow(
        backdrop_path = this.backdrop_path,
        first_air_date = this.first_air_date,
        genre_ids = this.genre_ids,
        id = this.id,
        name = this.name,
        origin_country = this.origin_country,
        original_language = this.original_language,
        original_name = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        mediaType = this.mediaType
    )
}

fun MovieDetailsDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        budget = this.budget,
        genres = this.genres?.map { it?.toGenre() },
        homepage = this.homepage,
        id = this.id,
        imdb_id = this.imdb_id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        revenue = this.revenue,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        credits = this.credits?.toCredits(),
        images = this.images?.toImages(),
        similar = this.similar?.toMovieResult()
    )
}

fun CreditsDto.toCredits(): Credits {
    return Credits(
        cast = this.cast?.map { it.toCast() }
    )
}

fun CastDto.toCast(): Cast {
    return Cast(
        adult = this.adult,
        cast_id = this.cast_id,
        character = this.character,
        credit_id = this.credit_id,
        gender = this.gender,
        id = this.id,
        known_for_department = this.known_for_department,
        name = this.name,
        order = this.order,
        original_name = this.original_name,
        popularity = this.popularity,
        profile_path = this.profile_path
    )
}

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name,
    )
}

fun ImagesDto.toImages(): Images {
    return Images(
        backdrops = this.backdrops?.map { it.toBackdrop() }!!
    )
}

fun BackdropsDto.toBackdrop(): Backdrops {
    return Backdrops(
        aspect_ratio = this.aspect_ratio,
        file_path = this.file_path,
        height = this.height,
        iso_639_1 = this.iso_639_1,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        width = this.width
    )
}

fun MovieResultDto.toMovieResult(): MovieResult {
    return MovieResult(
        page = this.page,
        results = this.results?.map { it.toMovie() },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun TvShowDetailDto.toTvShowDetail(): TvShowDetail {
    return TvShowDetail(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        credits = this.credits?.toCredits(),
        episode_run_time = this.episode_run_time,
        first_air_date = this.first_air_date,
        genres = this.genres?.map { it.toGenre() },
        homepage = this.homepage,
        id = this.id,
        images = this.images,
        in_production = this.in_production,
        languages = this.languages,
        last_air_date = this.last_air_date,
        last_episode_to_air = this.last_episode_to_air?.toTransformLastEpisodeToAir(),
        name = this.name,
        next_episode_to_air = this.next_episode_to_air?.toNextEpisodeToAir(),
        number_of_episodes = this.number_of_episodes,
        number_of_seasons = this.number_of_seasons,
        origin_country = this.origin_country,
        original_language = this.original_language,
        original_name = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        seasons = this.seasons?.map { it.toSeason() },
        similar = this.similar?.toTvShowResult(),
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
    )
}

fun NextEpisodeToAirDto.toNextEpisodeToAir(): NextEpisodeToAir {
    return NextEpisodeToAir(
        air_date = this.air_date,
        episode_number = this.episode_number,
        id = this.id,
        name = this.name,
        overview = this.overview,
        production_code = this.production_code,
        runtime = this.runtime,
        season_number = this.season_number,
        show_id = this.show_id,
        still_path = this.still_path,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

fun LastEpisodeToAirDto.toTransformLastEpisodeToAir(): LastEpisodeToAir {
    return LastEpisodeToAir(
        air_date = this.air_date,
        episode_number = this.episode_number,
        id = this.id,
        name = this.name,
        overview = this.overview,
        production_code = this.production_code,
        runtime = this.runtime,
        season_number = this.season_number,
        show_id = this.show_id,
        still_path = this.still_path,
        vote_average = this.vote_average
    )
}

fun SeasonDto.toSeason(): Season {
    return Season(
        air_date = this.air_date,
        episode_count = this.episode_count,
        id = this.id,
        name = this.name,
        overview = this.overview,
        poster_path = this.poster_path,
        season_number = this.season_number
    )
}

fun TvShowResultDto.toTvShowResult(): TvShowResult {
    return TvShowResult(
        page = this.page,
        results = this.results?.map { it.toTvShow() },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun TvShowSeasonDetailDto.toTvShowSeasonDetail(): TvShowSeasonDetail {
    return TvShowSeasonDetail(
        _id = this._id,
        air_date = this.air_date,
        episodes = this.episodes?.map { it.toEpisode() },
        id = this.id,
        name = this.name,
        overview = this.overview,
        poster_path = this.poster_path,
        season_number = this.season_number,
    )
}

fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        air_date = this.air_date,
        episode_number = this.episode_number,
        id = this.id,
        name = this.name,
        overview = this.overview,
        production_code = this.production_code,
        runtime = this.runtime,
        season_number = this.season_number,
        show_id = this.show_id,
        still_path = this.still_path,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

fun TvEpisodeDetailsDto.toTvEpisodeDetails(): TvEpisodeDetails {
    return TvEpisodeDetails(
        air_date = this.air_date,
        //credits = this.credits?.toCredits(),
        episode_number = this.episode_number,
        id = this.id,
        //images = this.images?.toImages(),
        name = this.name,
        overview = this.overview,
        production_code = this.production_code,
        runtime = this.runtime,
        season_number = this.season_number,
        still_path = this.still_path,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
    )
}