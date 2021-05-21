package com.test.marvel.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.marvel.BuildConfig
import com.test.marvel.data.exception.Failure
import com.test.marvel.data.extension.ToMd5
import com.test.marvel.data.server.model.CharactersResponse
import com.test.marvel.data.source.PreferencesDataSource
import com.test.marvel.data.source.RemoteDataSource
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class AuthRepositoryTest {
    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var preferencesDataSource: PreferencesDataSource

    lateinit var characterRepository : CharactersRepository


    @Before
    fun setUp() {
        characterRepository = CharactersRepository(remoteDataSource, preferencesDataSource)
    }

    @Test
    fun `get characters successful`() {
        runBlocking {
            val charactersResponse = CharactersResponse(null,null,null,null,null,null,null)
            whenever(remoteDataSource.getCharacters(any(), any(), any())).thenReturn(
                    Response.success(charactersResponse)
            )

            val result = characterRepository.getCharacters( )
            result.isRight shouldBe true
        }
    }


}