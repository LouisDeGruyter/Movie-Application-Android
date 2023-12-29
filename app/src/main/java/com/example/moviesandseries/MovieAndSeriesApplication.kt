package com.example.moviesandseries

import android.app.Application
import android.content.Context
import com.example.moviesandseries.data.AppContainer
import com.example.moviesandseries.data.DefaultAppContainer

class MovieAndSeriesApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContext = this // this has to be done before initializing the container as it uses appContext
        container = DefaultAppContainer(this)
    }
    companion object {
        lateinit var appContext: Context
    }
}
