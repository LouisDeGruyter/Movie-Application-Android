package com.example.moviesandseries.network

/**
 * Object containing API endpoints for The Movie Database (TMDb) requests.
 */
object ApiEndpoints {
    // Base URL for movie posters
    const val Poster = "https://image.tmdb.org/t/p/w780/"

    // Movie Endpoints
    const val Movies = "discover/movie"
    const val MovieDetail = "movie/{movie_id}"
    const val MoviesInTheaters = "movie/now_playing"
    const val MoviesPopular = "movie/popular"
    const val MoviesTopRated = "movie/top_rated"
    const val MoviesUpcoming = "movie/upcoming"

    // Series Endpoints
    const val Series = "discover/tv"
    const val SeriesDetail = "tv/{series_id}"
    const val SeriesPopular = "tv/popular"
    const val SeriesTopRated = "tv/top_rated"
    const val SeriesOnTheAir = "tv/on_the_air"
    const val SeriesAiringToday = "tv/airing_today"

    // Other Endpoints
    const val Collections = "collection/{collection_id}"
    const val SeasonDetail = "tv/{tv_id}/season/{season_number}"
}
