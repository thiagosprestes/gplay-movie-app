package com.example.globoplay.features.home.di

import com.example.globoplay.core.data.remote.datasource.MovieService
import com.example.globoplay.features.home.data.repository.MovieRepositoryImpl
import com.example.globoplay.features.home.data.source.MovieRemoteDataSourceImpl
import com.example.globoplay.features.home.domain.repository.MovieRepository
import com.example.globoplay.features.home.domain.source.MovieRemoteDataSource
import com.example.globoplay.features.home.domain.usecase.GetMoviesUseCase
import com.example.globoplay.features.home.domain.usecase.GetMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureModule {
    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(dataSource: MovieRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCaseImpl(moviePopularRepository)
    }
}