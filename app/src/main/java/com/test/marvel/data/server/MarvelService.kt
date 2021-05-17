package com.test.marvel.data.server


import com.test.marvel.data.server.model.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface MarvelService {
    @POST("login")
    suspend fun login(@Query("email") email : String, @Query("password") password : String) : Response<LoginResponse>

//    @POST("receivePoll")
//    suspend fun sendSurveyAnswers(@Body survey : Survey) : Response<CommonResponse>


}