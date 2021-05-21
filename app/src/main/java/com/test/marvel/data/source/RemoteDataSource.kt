package com.test.marvel.data.source

import com.test.marvel.data.server.model.CharactersResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getCharacters( md5Digest: String, timestamp: String, apikey: String): Response<CharactersResponse>
    suspend fun getCharacterDetail(id : Int?, md5Digest: String, timestamp: String, apikey: String): Response<CharactersResponse>
}