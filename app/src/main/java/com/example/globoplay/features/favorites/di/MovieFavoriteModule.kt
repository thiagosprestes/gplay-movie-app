package com.example.globoplay.features.favorites.di

import com.example.globoplay.core.data.local.dao.MovieDao
import com.example.globoplay.features.favorites.data.repository.MovieFavoriteRepositoryImpl
import com.example.globoplay.features.favorites.data.source.MovieFavoriteLocalDataSourceImpl
import com.example.globoplay.features.favorites.domain.repository.MovieFavoriteRepository
import com.example.globoplay.features.favorites.domain.source.MovieFavoriteLocalDataSource
import com.example.globoplay.features.favorites.domain.usecase.AddMovieFavoriteUseCase
import com.example.globoplay.features.favorites.domain.usecase.AddMovieFavoriteUseCaseImpl
import com.example.globoplay.features.favorites.domain.usecase.DeleteMovieFavoriteUseCase
import com.example.globoplay.features.favorites.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import com.example.globoplay.features.favorites.domain.usecase.GetMovieFavoriteUseCase
import com.example.globoplay.features.favorites.domain.usecase.GetMovieFavoriteUseCaseImpl
import com.example.globoplay.features.favorites.domain.usecase.IsMovieFavoriteUseCase
import com.example.globoplay.features.favorites.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {
    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource {
        return MovieFavoriteLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(dataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideAddMovieFavoriteUseCase(repository: MovieFavoriteRepository): AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(repository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetMoviesFavoriteUseCase(repository: MovieFavoriteRepository): GetMovieFavoriteUseCase {
        return GetMovieFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideIsMoviesFavoriteUseCase(repository: MovieFavoriteRepository): IsMovieFavoriteUseCase {
        return IsMovieFavoriteUseCaseImpl(repository)
    }
}