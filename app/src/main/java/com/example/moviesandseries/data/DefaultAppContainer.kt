package com.example.moviesandseries.data

import android.content.Context
import android.content.pm.PackageManager
import com.example.moviesandseries.MovieAndSeriesApplication
import com.example.moviesandseries.data.database.MovieAndSeriesApplicationDb
import com.example.moviesandseries.network.CollectionApiService
import com.example.moviesandseries.network.MovieApiService
import com.example.moviesandseries.network.NetworkConnectionInterceptor
import com.example.moviesandseries.network.SeriesApiService
import com.example.moviesandseries.repository.CollectionRepository
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.NetworkCollectionRepository
import com.example.moviesandseries.repository.NetworkMovieRepository
import com.example.moviesandseries.repository.NetworkSeriesRepository
import com.example.moviesandseries.repository.SeriesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

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
private class LoggingInterceptorHeaders : Interceptor {
    var TMDB_API_KEY = getMetadata(MovieAndSeriesApplication.appContext, "TMDB_API_KEY") ?: ""

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request: Request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $TMDB_API_KEY").build()
        return chain.proceed(request)
    }
}
class DefaultAppContainer(private val context: Context) : AppContainer {
    private val networkCheck = NetworkConnectionInterceptor(context)
    private val baseUrl = "https://api.themoviedb.org/3/"
    private val posterUrl = "https://image.tmdb.org/t/p/original/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // create logging interceptor

    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

    // create OkHttp Client
    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(LoggingInterceptorHeaders())
        .addInterceptor(networkCheck)
        .build()

    // create retrofit object
    private val retrofit: retrofit2.Retrofit = retrofit2.Retrofit.Builder()
        .addConverterFactory(retrofit2.converter.moshi.MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()


    /**
     * Create an instance of the API interface using the Retrofit instance to inject into the repositories
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

    /**
     * DI implementation for blog post repository.
     */
    override val movieRepository: MovieRepository by lazy {
        NetworkMovieRepository(movieApiService, MovieAndSeriesApplicationDb.getDatabase(context = context).movieDao())
    }
    override val seriesRepository: SeriesRepository by lazy {
        NetworkSeriesRepository(seriesApiService)
    }
    override val collectionRepository: CollectionRepository by lazy {
        NetworkCollectionRepository(collectionApiService)
    }
}
