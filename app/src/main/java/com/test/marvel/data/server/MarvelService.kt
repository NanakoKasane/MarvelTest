package com.test.marvel.data.server


import com.test.marvel.data.server.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.*

const val ID = "id"
const val HASH = "hash"
const val TIMESTAMP = "ts"
const val APIKEY = "apikey"

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
            @Query(HASH) md5Digest: String,
            @Query(TIMESTAMP) timestamp: String,
            @Query(APIKEY) apikey: String
    ) : Response<CharactersResponse>

    @GET("characters/{id}")
    suspend fun getCharacterDetail(
            @Path(ID) id : Int?,
            @Query(HASH) md5Digest: String,
            @Query(TIMESTAMP) timestamp: String,
            @Query(APIKEY) apikey: String
    ) : Response<CharactersResponse>


}