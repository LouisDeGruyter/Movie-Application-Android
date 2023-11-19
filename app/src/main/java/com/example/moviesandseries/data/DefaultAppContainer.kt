package com.example.moviesandseries.data

import android.content.Context
import android.content.pm.PackageManager
import com.example.moviesandseries.MovieAndSeriesApplication
import com.example.moviesandseries.network.MovieApiService
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.NetworkMovieRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import android.util.Log
import com.example.moviesandseries.network.SeriesApiService
import com.example.moviesandseries.repository.NetworkSeriesRepository
import com.example.moviesandseries.repository.SeriesRepository

fun getMetadata(context: Context, key: String?): String? {
    try {
        val metaData = context.packageManager
            .getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            ).metaData
        val value = metaData?.getString(key)
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
            chain.request().newBuilder().addHeader("Authorization","Bearer "+ TMDB_API_KEY).build()
        Log.d("LoggingInterceptor", "api-key: $TMDB_API_KEY")
        Log.d("LoggingInterceptor", "request: $request")
        return chain.proceed(request)
    }
}
class DefaultAppContainer : AppContainer {

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


    /**
     * DI implementation for blog post repository.
     */
    override val movieRepository: MovieRepository by lazy{
        NetworkMovieRepository(movieApiService)
    }
    override val seriesRepository: SeriesRepository by lazy{
        NetworkSeriesRepository(seriesApiService)
    }
}
