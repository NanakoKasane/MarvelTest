package com.test.marvel.di

import androidx.annotation.NonNull
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import java.util.*
import javax.inject.Singleton

@Module
class EnvironmentModule {

    @Singleton
    @OkHttpNetworkInterceptors
    @NonNull
    @Provides
    fun providesNetworkInterceptors(): List<Interceptor> {
        return Collections.singletonList(StethoInterceptor())/*listOf<@JvmSuppressWildcards Interceptor>(StethoInterceptor())*/
    }

}