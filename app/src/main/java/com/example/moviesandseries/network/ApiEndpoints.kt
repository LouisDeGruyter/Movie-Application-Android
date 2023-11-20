package com.example.moviesandseries.network

object ApiEndpoints {
    const val Poster= "https://image.tmdb.org/t/p/original/"
    const val Movies = "discover/movie"
    const val MovieDetail = "movie/{movie_id}"
    const val MoviesInTheaters = "movie/now_playing"
    const val MoviesPopular= "movie/popular"
    const val MoviesTopRated= "movie/top_rated"
    const val MoviesUpcoming= "movie/upcoming"
    const val Series = "discover/tv"
    const val SeriesDetail = "tv/{series_id}"
    const val SeriesPopular = "tv/popular"
    const val SeriesTopRated = "tv/top_rated"
    const val SeriesOnTheAir = "tv/on_the_air"
    const val SeriesAiringToday = "tv/airing_today"
}