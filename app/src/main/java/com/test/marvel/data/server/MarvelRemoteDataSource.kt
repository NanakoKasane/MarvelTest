package com.test.marvel.data.server

import com.test.marvel.data.source.RemoteDataSource
import javax.inject.Inject

class MarvelRemoteDataSource @Inject constructor(private val marvelService: MarvelService) : RemoteDataSource {
    override suspend fun login(username: String, pin : String) =
            marvelService.login("$username@admin.com", pin)

}