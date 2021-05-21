package com.test.marvel.data.repository

import com.test.marvel.BuildConfig
import com.test.marvel.data.exception.Failure
import com.test.marvel.data.extension.ToMd5
import com.test.marvel.data.functional.Either
import com.test.marvel.data.source.PreferencesDataSource
import com.test.marvel.data.source.RemoteDataSource
import com.test.marvel.data.toDomainCharacterList
import com.test.marvel.domain.Character
import java.lang.Exception
import java.net.ConnectException
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                               private val preferencesDataSource: PreferencesDataSource){

    suspend fun getCharacters(): Either<Failure, List<Character>?> = try {
        val timestamp = System.currentTimeMillis().toString()
        val response = remoteDataSource.getCharacters((timestamp + BuildConfig.MARVEL_APIKEY_PRIVATE + BuildConfig.MARVEL_APIKEY_PUBLIC).ToMd5(),
                timestamp, BuildConfig.MARVEL_APIKEY_PUBLIC )
        when (response.isSuccessful) {
            true -> response.body()?.let {
                Either.Right(it.data?.toDomainCharacterList())
            }
                    ?: Either.Left(Failure.ServerError)
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: IllegalArgumentException) {
        Either.Left(Failure.IllegalArgumentFailure())
    } catch (exception: ConnectException) {
        Either.Left(Failure.ServerError)
    } catch (exception : Exception){
        Either.Left(Failure.ServerError)
    }

    suspend fun getCharacterDetail(id : Int?): Either<Failure, Character?> = try {
        val timestamp = System.currentTimeMillis().toString()
        val response = remoteDataSource.getCharacterDetail(id, (timestamp + BuildConfig.MARVEL_APIKEY_PRIVATE + BuildConfig.MARVEL_APIKEY_PUBLIC).ToMd5(),
                timestamp, BuildConfig.MARVEL_APIKEY_PUBLIC )
        when (response.isSuccessful) {
            true -> response.body()?.let {
                if (it.data?.toDomainCharacterList()?.isNotEmpty() == true) {
                    Either.Right(it.data.toDomainCharacterList()[0])
                } else
                    Either.Left(Failure.Empty)
            }
                    ?: Either.Left(Failure.ServerError)
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: IllegalArgumentException) {
        Either.Left(Failure.IllegalArgumentFailure())
    } catch (exception: ConnectException) {
        Either.Left(Failure.ServerError)
    } catch (exception : Exception){
        Either.Left(Failure.ServerError)
    }


}