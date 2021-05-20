package com.test.marvel.data.server

import com.test.marvel.data.server.model.CharactersResponse
import com.test.marvel.data.source.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class MarvelRemoteDataSource @Inject constructor(private val marvelService: MarvelService) : RemoteDataSource {

    override suspend fun getCharacters(md5Digest: String, timestamp: String, apikey: String): Response<CharactersResponse> =
        marvelService.getCharacters(md5Digest, timestamp, apikey)



}