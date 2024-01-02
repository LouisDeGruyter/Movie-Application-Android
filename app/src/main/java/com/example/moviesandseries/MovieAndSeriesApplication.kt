package com.example.moviesandseries

import android.app.Application
import android.content.Context
import com.example.moviesandseries.data.AppContainer
import com.example.moviesandseries.data.DefaultAppContainer

/**
 * Application class for the MoviesAndSeries app, responsible for initializing the AppContainer.
 */
class MovieAndSeriesApplication : Application() {

    /** AppContainer instance used by the rest of the classes to obtain dependencies */
    lateinit var container: AppContainer

    /**
     * Overrides the onCreate method to initialize the AppContainer and set up the application context.
     */
    override fun onCreate() {
        super.onCreate()
        // Set the application context
        appContext = this
        // Initialize the AppContainer
        container = DefaultAppContainer(this)
    }

    companion object {
        /** Application context accessible throughout the app */
        lateinit var appContext: Context
    }
}
