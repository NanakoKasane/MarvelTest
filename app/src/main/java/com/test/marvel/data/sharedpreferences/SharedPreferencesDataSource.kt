package com.test.marvel.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.test.marvel.R
import com.test.marvel.data.source.PreferencesDataSource
import javax.inject.Inject
import javax.inject.Singleton


private const val USER_DATA = "userdata"

@Singleton
class SharedPreferencesDataSource @Inject constructor(context: Context, val gson : Gson) : PreferencesDataSource {
    private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    override fun saveUserData(userData: Any) {
//        setString(USER_DATA, gson.toJson(userData))

    }

    override fun getUserData(): Any? {
//        val userDataString = getString(USER_DATA, null)
//        return if (userDataString == null )
//            null
//        else
//            gson.fromJson(userDataString, UserData::class.java)


        return null
    }





    // Generics

    private fun getBoolean(key: String, defaultValue: Boolean = false): Boolean =
            sharedPreferences.getBoolean(key, defaultValue)

    private fun setBoolean(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    private fun getString(key: String, defaultValue: String? = null): String? =
            sharedPreferences.getString(key, defaultValue)

    private fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    private fun getLong(key: String, defaultValue: Long): Long =
            sharedPreferences.getLong(key, defaultValue)

    private fun setLong(key: String, value: Long) {
        with(sharedPreferences.edit()) {
            putLong(key, value)
            apply()
        }
    }

    private fun getInt(key: String, defaultValue: Int): Int =
            sharedPreferences.getInt(key, defaultValue)

    private fun setInt(key: String, value: Int) {
        with(sharedPreferences.edit()) {
            putInt(key, value)
            apply()
        }
    }
}