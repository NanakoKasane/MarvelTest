package com.test.marvel.data.extension

import com.facebook.stetho.Stetho
import com.test.marvel.App
import com.test.marvel.BuildConfig
import timber.log.Timber

fun App.configure() {
    if (BuildConfig.DEBUG){
        Timber.plant(Timber.DebugTree())
    }
    Timber.d("debug")
    Stetho.initializeWithDefaults(this)
}