package com.example.moviesandseries.data.database.db
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesandseries.data.database.converters.CollectionConverter
import com.example.moviesandseries.data.database.converters.GenreConverter
import com.example.moviesandseries.data.database.converters.MovieConverter
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.data.database.db.asDbObject
@Entity(tableName = "movies")
@TypeConverters(CollectionConverter::class, GenreConverter::class, MovieConverter::class)
data class DbMovie(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var belongsToCollection: DbCollection,
    var budget: Int = 0,
    var genres: List<DbGenre> = listOf(),
    var homepage: String = "",
    @PrimaryKey
    var id: Int = 0,
    var imdbId: String = "",
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var productionCompanies: List<DbProductionCompany> = listOf(),
    var productionCountries: List<DbProductionCountry> = listOf(),
    var releaseDate: String = "",
    var revenue: Int = 0,
    var runtime: Int = 0,
    var spokenLanguages: List<DbSpokenLanguage> = listOf(),
    var status: String = "",
    var tagline: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var isFavorite: Boolean = false,
)
fun MovieDetail.asDbObject(): DbMovie = DbMovie(
    adult = this.adult,
    backdropPath = this.backdropPath,
    belongsToCollection = this.belongsToCollection.asDbObject(),
    budget = this.budget,
    genres = this.genres.map { it.asDbObject() },
    homepage = this.homepage,
    id = this.id,
    imdbId = this.imdbId,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    productionCompanies = this.productionCompanies.map { it.asDbObject() },
    productionCountries = this.productionCountries.map { it.asDbObject() },
    releaseDate = this.releaseDate,
    revenue = this.revenue,
    runtime = this.runtime,
    spokenLanguages = this.spokenLanguages.map { it.asDbObject() },
    status = this.status,
    tagline = this.tagline,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    isFavorite = false,
)
fun DbMovie.asDomainObject(): MovieDetail = MovieDetail(
    adult = this.adult,
    backdropPath = this.backdropPath,
    belongsToCollection = this.belongsToCollection.asDomainObject(),
    budget = this.budget,
    genres = this.genres.map { it.asDomainObject() },
    homepage = this.homepage,
    id = this.id,
    imdbId = this.imdbId,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    productionCompanies = this.productionCompanies.map { it.asDomainObject() },
    productionCountries = this.productionCountries.map { it.asDomainObject() },
    releaseDate = this.releaseDate,
    revenue = this.revenue,
    runtime = this.runtime,
    spokenLanguages = this.spokenLanguages.map { it.asDomainObject() },
    status = this.status,
    tagline = this.tagline,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
)
fun List<DbMovie>.asDomainObject() = this.map { it.asDomainObject() }
