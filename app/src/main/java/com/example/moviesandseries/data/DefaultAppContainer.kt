package com.example.moviesandseries.data

import android.content.Context
import android.content.pm.PackageManager
import com.example.moviesandseries.MovieAndSeriesApplication
import com.example.moviesandseries.data.database.MovieAndSeriesApplicationDb
import com.example.moviesandseries.network.CollectionApiService
import com.example.moviesandseries.network.MovieApiService
import com.example.moviesandseries.network.NetworkConnectionInterceptor
import com.example.moviesandseries.network.SeasonApiService
import com.example.moviesandseries.network.SeriesApiService
import com.example.moviesandseries.repository.CollectionRepository
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.NetworkCollectionRepository
import com.example.moviesandseries.repository.NetworkMovieRepository
import com.example.moviesandseries.repository.NetworkSeasonRepository
import com.example.moviesandseries.repository.NetworkSeriesRepository
import com.example.moviesandseries.repository.SeasonRepository
import com.example.moviesandseries.repository.SeriesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Utility function to retrieve metadata from the AndroidManifest.xml.
 * @param context The context of the application.
 * @param key The key to look up in the metadata.
 * @return The value associated with the key in the metadata.
 */
fun getMetadata(context: Context, key: String?): String? {
    try {
        val metaData = context.packageManager
            .getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA,
            ).metaData
        return metaData?.getString(key)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    return null
}

/**
 * Interceptor class to add headers, including the TMDB API key, to the HTTP request.
 */
private class LoggingInterceptorHeaders : Interceptor {
    var TMDB_API_KEY = getMetadata(MovieAndSeriesApplication.appContext, "TMDB_API_KEY") ?: ""

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request: Request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $TMDB_API_KEY").build()
        return chain.proceed(request)
    }
}

/**
 * The default implementation of the [AppContainer] interface.
 * This class provides instances of various components like repositories, services, and interceptors.
 * @property context The context of the application.
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    // Interceptor for checking network connection
    private val networkCheck = NetworkConnectionInterceptor(context)

    // Base URLs for the API and poster images
    private val baseUrl = "https://api.themoviedb.org/3/"

    // Moshi instance for JSON parsing
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // HTTP request/response logging interceptor
    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

    // OkHttpClient with various interceptors
    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(LoggingInterceptorHeaders())
        .addInterceptor(networkCheck)
        .build()

    // Retrofit instance for network requests
    private val retrofit: retrofit2.Retrofit = retrofit2.Retrofit.Builder()
        .addConverterFactory(retrofit2.converter.moshi.MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    /**
     * Create an instance of the API interface using the Retrofit instance to inject into the repositories.
     */
    private val movieApiService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
    private val seriesApiService: SeriesApiService by lazy {
        retrofit.create(SeriesApiService::class.java)
    }
    private val collectionApiService: CollectionApiService by lazy {
        retrofit.create(CollectionApiService::class.java)
    }
    private val seasonApiService: SeasonApiService by lazy {
        retrofit.create(SeasonApiService::class.java)
    }

    /**
     * DI implementation for movie repository.
     */
    override val movieRepository: MovieRepository by lazy {
        NetworkMovieRepository(
            movieApiService,
            MovieAndSeriesApplicationDb.getDatabase(context = context).movieDao()
        )
    }

    /**
     * DI implementation for series repository.
     */
    override val seriesRepository: SeriesRepository by lazy {
        NetworkSeriesRepository(
            seriesApiService,
            MovieAndSeriesApplicationDb.getDatabase(context = context).seriesDao()
        )
    }

    /**
     * DI implementation for collection repository.
     */
    override val collectionRepository: CollectionRepository by lazy {
        NetworkCollectionRepository(collectionApiService)
    }

    /**
     * DI implementation for season repository.
     */
    override val seasonRepository: SeasonRepository by lazy {
        NetworkSeasonRepository(seasonApiService)
    }
}
