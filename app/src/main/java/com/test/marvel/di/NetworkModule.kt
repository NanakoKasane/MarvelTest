package com.test.marvel.di

import androidx.annotation.NonNull
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.marvel.BuildConfig
import com.test.marvel.data.server.MarvelRemoteDataSource
import com.test.marvel.data.server.MarvelService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import com.test.marvel.data.sharedpreferences.SharedPreferencesDataSource
import com.test.marvel.data.source.RemoteDataSource


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@OkHttpNetworkInterceptors @NonNull networkInterceptors: List<@JvmSuppressWildcards Interceptor>,
                            sharedPreferencesDataSource: SharedPreferencesDataSource): OkHttpClient {
        val builder = OkHttpClient.Builder()

                .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .hostnameVerifier(HostnameVerifier { _, _ -> true })
        for (interceptor in networkInterceptors)
            builder.addNetworkInterceptor(interceptor)

        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val requestBuilder = chain.request().newBuilder()
                // TODO in case need authorization:
//                if (sharedPreferencesDataSource.getAccessToken() != null ){
//                    requestBuilder.addHeader("Authorization", "Bearer ${sharedPreferencesDataSource.getAccessToken()}")
//                }
                requestBuilder.addHeader("Content-Type","application/json")
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                return chain.proceed(requestBuilder.build())
            }
        })
        return builder.build()
    }


    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create() // .excludeFieldsWithoutExposeAnnotation()

    @Provides
    fun provideApi(retrofit: Retrofit): MarvelService = retrofit.create(MarvelService::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()

    @Provides
    fun provideNetworkInterceptors():  List<@JvmSuppressWildcards Interceptor> = listOf(StethoInterceptor())

    @Provides
    fun provideRemoteDataSource(service: MarvelService) : RemoteDataSource {
        return MarvelRemoteDataSource(service)
    }
}