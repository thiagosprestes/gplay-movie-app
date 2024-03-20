package com.example.globoplay.features.home.di

import com.example.globoplay.core.data.remote.MovieService
import com.example.globoplay.core.data.remote.repository.MoviePopularRepositoryImpl
import com.example.globoplay.features.home.data.source.MoviePopularRemoteDataSourceImpl
import com.example.globoplay.features.home.domain.repository.MoviePopularRepository
import com.example.globoplay.features.home.domain.source.MoviePopularRemoteDataSource
import com.example.globoplay.features.home.domain.usecase.GetPopularMoviesUseCase
import com.example.globoplay.features.home.domain.usecase.GetPopularMoviesUseCaseImpl
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
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource {
        return MoviePopularRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(dataSource: MoviePopularRemoteDataSource): MoviePopularRepository {
        return MoviePopularRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: MoviePopularRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(moviePopularRepository)
    }
}