package com.test.marvel

import android.app.Application
import com.test.marvel.data.extension.configure
import com.test.marvel.di.AppComponent
import com.test.marvel.di.DaggerAppComponent

open class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        configure()
    }
}