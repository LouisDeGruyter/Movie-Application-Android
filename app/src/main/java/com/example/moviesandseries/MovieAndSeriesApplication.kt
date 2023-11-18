package com.example.moviesandseries

import android.app.Application
import com.example.moviesandseries.data.AppContainer
import com.example.moviesandseries.data.DefaultAppContainer

class MovieAndSeriesApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
