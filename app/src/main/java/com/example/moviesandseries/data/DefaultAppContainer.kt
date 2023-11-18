package com.example.moviesandseries.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.themoviedb.org/3/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // create logging interceptor
    private class LoggingInterceptorHeaders : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request: Request = chain.request().newBuilder().addHeader("api-key", "").build()
            return chain.proceed(request)
        }
    }
    private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

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
}
