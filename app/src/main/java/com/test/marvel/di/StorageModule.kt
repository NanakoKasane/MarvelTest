package com.test.marvel.di

import com.test.marvel.data.sharedpreferences.SharedPreferencesDataSource
import com.test.marvel.data.source.PreferencesDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideSharedPreferences(storage: SharedPreferencesDataSource): PreferencesDataSource

}