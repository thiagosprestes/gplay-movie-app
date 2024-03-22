package com.example.globoplay.features.movieDetail.di

import com.example.globoplay.core.data.remote.datasource.MovieService
import com.example.globoplay.features.movieDetail.data.repository.MovieDetailsRepositoryImpl
import com.example.globoplay.features.movieDetail.data.source.MovieDetailsRemoteDataSourceImpl
import com.example.globoplay.features.movieDetail.domain.repository.MovieDetailsRepository
import com.example.globoplay.features.movieDetail.domain.source.MovieDetailsRemoteDataSource
import com.example.globoplay.features.movieDetail.domain.usecase.GetMovieDetailsUseCase
import com.example.globoplay.features.movieDetail.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {
    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository)
    }
}