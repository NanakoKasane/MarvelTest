package com.test.marvel.data.repository

import com.test.marvel.data.exception.Failure
import com.test.marvel.data.functional.Either
import com.test.marvel.data.server.model.LoginResponse
import com.test.marvel.data.source.PreferencesDataSource
import com.test.marvel.data.source.RemoteDataSource
import java.lang.Exception
import java.net.ConnectException
import javax.inject.Inject

class AuthRepository @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                         private val preferencesDataSource: PreferencesDataSource){

    suspend fun login(email: String, password: String): Either<Failure, LoginResponse> = try {
        val response = remoteDataSource.login(email, password)
        when (response.isSuccessful) {
            true -> response.body()?.let {
                if (it.user != null && it.access_token != null){
//                    preferencesDataSource.saveUserData(it.toDomain())
//                    preferencesDataSource.saveAccessToken(it.access_token)
//                    Either.Right(it.toDomain())
                    // TODO


                    Either.Right(it)
                }
                else if (it.message != null ){
                    Either.Left(Failure.CustomFailure(it.message))
                } else
                    Either.Left(Failure.ServerError)
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


//
//    // TODO.
//    suspend fun resetPin(clinicHistory : String): Either<Failure, CommonResponse> = try {
//        val response = remoteDataSource.resetPin(clinicHistory)
//        when (response.isSuccessful) {
//            true -> response.body()?.let {
//                if (it.success == true){
//                    Either.Right(it)
//                } else if (it.success != true && it.message != null){
//                    Either.Left(Failure.CustomFailure(it.message))
//                } else
//                    Either.Left(Failure.ServerError)
//            }
//                    ?: Either.Left(Failure.ServerError)
//            false -> Either.Left(Failure.ServerError)
//        }
//    } catch (exception: IllegalArgumentException) {
//        Either.Left(Failure.IllegalArgumentFailure())
//    } catch (exception: ConnectException) {
//        Either.Left(Failure.ServerError)
//    } catch (exception : Exception){
//        Either.Left(Failure.ServerError)
//    }


}