package com.test.marvel.data.source

import com.test.marvel.data.server.model.CharactersResponse
import com.test.marvel.data.server.model.LoginResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getCharacters( md5Digest: String, timestamp: String, apikey: String): Response<CharactersResponse>
}