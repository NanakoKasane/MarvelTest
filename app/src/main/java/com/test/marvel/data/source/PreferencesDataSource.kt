package com.test.marvel.data.source

interface PreferencesDataSource {
    fun saveUserData(userData: Any)
    fun getUserData() : Any?
}