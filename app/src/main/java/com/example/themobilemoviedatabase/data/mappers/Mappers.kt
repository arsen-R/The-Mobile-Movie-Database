package com.example.themobilemoviedatabase.data.mappers

import com.example.themobilemoviedatabase.data.database.entity.MovieDetailEntity
import com.example.themobilemoviedatabase.data.database.entity.TvShowDetailEntity
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
        backdrops = this.backdrops?.map { it.toBackdrop() }!!,
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
        crew = crew?.map { it.toCrew() },
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

fun CastResultDto.toCastResult(): CastResult {
    return CastResult(
        cast = this.cast?.map { it.toCast() }
    )
}

fun CrewDto.toCrew(): Crew {
    return Crew(
        adult = this.adult,
        credit_id = this.credit_id,
        department = this.department,
        gender = this.gender,
        id = this.id,
        job = this.job,
        known_for_department = this.known_for_department,
        name = this.name,
        original_name = this.original_name,
        popularity = this.popularity,
        profile_path = this.profile_path
    )
}

fun StillResultDto.toStillResult(): StillsResult {
    return StillsResult(
        id = this.id,
        stills = this.stills?.map { it.toBackdrop() }
    )
}

fun AuthorDetailsDto.toAuthorDetails(): AuthorDetails {
    return AuthorDetails(
        avatar_path = this.avatar_path,
        name = this.name,
        rating = this.rating,
        username = this.username
    )
}

fun ReviewDto.toReview(): Review {
    return Review(
        author = author,
        author_details = author_details?.toAuthorDetails(),
        content = this.content,
        created_at = this.created_at,
        id = this.id,
        updated_at = this.updated_at,
        url = this.url
    )
}

fun ReviewResultDto.toReviewResult(): ReviewResult {
    return ReviewResult(
        id = this.id,
        page = this.page,
        results = this.results?.map { it.toReview() },
        total_pages = this.total_pages,
        total_results = this.total_results
    )
}

fun ReviewDetailDto.toReviewDetail(): ReviewDetail {
    return ReviewDetail(
        author = this.author,
        author_details = this.author_details?.toAuthorDetails(),
        content = this.content,
        created_at = this.created_at,
        id = this.id,
        iso_639_1 = this.iso_639_1,
        media_id = this.media_id,
        media_title = this.media_title,
        media_type = this.media_type,
        updated_at = this.updated_at,
        url = this.url
    )
}

fun PersonDto.toPerson(): Person {
    return Person(
        adult = this.adult,
        also_known_as = this.also_known_as,
        biography = this.biography,
        birthday = this.birthday,
        deathday = this.deathday,
        gender = this.gender,
        homepage = this.homepage,
        id = this.id,
        imdb_id = this.imdb_id,
        known_for_department = this.known_for_department,
        name = this.name,
        place_of_birth = this.place_of_birth,
        popularity = this.popularity,
        profile_path = this.profile_path,
        images = this.images?.toPersonImage(),
        combineCredits = this.combineCredits?.toPersonFilmography()
    )
}

fun PersonImageDto.toPersonImage(): PersonImage {
    return PersonImage(
        profiles = this.profiles?.map { it?.toProfile() }
    )
}

fun ProfileDto.toProfile(): Profile {
    return Profile(
        aspect_ratio = this.aspect_ratio,
        file_path = this.file_path,
        height = this.height,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        width = this.width
    )
}

fun PersonFilmographyDto.toPersonFilmography(): PersonFilmography {
    return PersonFilmography(
        cast = this.cast?.map { it.toPersonCast() }
    )
}

fun PersonCastDto.toPersonCast(): PersonCast {
    return PersonCast(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        character = this.character,
        credit_id = this.credit_id,
        genre_ids = this.genre_ids,
        id = this.id,
        media_type = this.media_type,
        name = this.name,
        order = this.order,
        origin_country = this.origin_country,
        original_language = this.original_language,
        original_name = this.original_name,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        first_air_date = this.first_air_date
    )
}

fun FilmographyResultDto.toFilmographyResult(): FilmographyResult {
    return FilmographyResult(
        cast = this.cast?.map { it.toPersonCast() },
        id = this.id
    )
}

fun SearchResultDto.toSearchResult(): SearchResult {
    return SearchResult(
        page = this.page,
        results = this.results?.map { it.toMultiSearch() },
        total_pages = this.total_pages,
        total_results = this.total_results
    )
}

fun MultiSearchDto.toMultiSearch(): MultiSearch {
    return MultiSearch(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        first_air_date = this.first_air_date,
        gender = this.gender,
        genre_ids = this.genre_ids,
        id = this.id,
        known_for_department = this.known_for_department,
        media_type = this.media_type,
        name = this.name,
        origin_country = this.origin_country,
        original_language = this.original_language,
        original_name = this.original_name,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        profile_path = this.profile_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

fun MovieDetailEntity.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        budget = this.budget,
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
    )
}

fun TvShowDetailEntity.toTvShowDetail(): TvShowDetail {
    return TvShowDetail(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        first_air_date = this.first_air_date,
        homepage = this.homepage,
        id = this.id,
        in_production = this.in_production,
        last_air_date = this.last_air_date,
        name = this.name,
        number_of_episodes = this.number_of_episodes,
        number_of_seasons = this.number_of_seasons,
        original_language = this.original_language,
        original_name = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
    )
}

fun MovieDetail.toMovieDetailEntity(): MovieDetailEntity {
    return MovieDetailEntity(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        budget = this.budget,
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
    )
}
fun TvShowDetail.toTvShowDetailEntity(): TvShowDetailEntity {
    return TvShowDetailEntity(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        first_air_date = this.first_air_date,
        homepage = this.homepage,
        id = this.id,
        in_production = this.in_production,
        last_air_date = this.last_air_date,
        name = this.name,
        number_of_episodes = this.number_of_episodes,
        number_of_seasons = this.number_of_seasons,
        original_language = this.original_language,
        original_name = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
    )
}
