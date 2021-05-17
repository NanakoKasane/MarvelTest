package com.test.marvel.data.source

import com.test.marvel.data.server.model.LoginResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun login(username: String, pin: String): Response<LoginResponse>
}