package com.example.globoplay.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.globoplay.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM Movies WHERE movieId = :movieId")
    suspend fun isFavorite(movieId:Int):MovieEntity?

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)
}