package com.example.globoplay.core.di

import com.example.globoplay.core.data.remote.MovieDataSource
import com.example.globoplay.core.data.remote.ParamsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideParamsInterceptor(): ParamsInterceptor {
        return ParamsInterceptor()
    }

    @Provides
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(paramsInterceptor).build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideMovieDataSource(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): MovieDataSource {
        return Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(MovieDataSource::class.java)
    }
}