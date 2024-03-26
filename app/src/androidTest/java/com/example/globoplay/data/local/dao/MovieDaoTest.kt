package com.example.globoplay.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.globoplay.core.data.local.MovieDatabase
import com.example.globoplay.core.data.local.dao.MovieDao
import com.example.globoplay.core.data.local.entity.MovieEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named
import com.google.common.truth.Truth.assertThat

@HiltAndroidTest
@SmallTest
class MovieDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: MovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun setup() {
        hiltRule.inject()
        movieDao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getMovies() = runTest {
        val movies = movieDao.getMovies().first()

        assertThat(movies.size).isEqualTo(0)
    }

    @Test
    fun getMoviesOrderedById() = runTest {
        val moviesEntities = listOf(
            MovieEntity(movieId = 1, title = "Vingadores", imageUrl = "url"),
            MovieEntity(movieId = 2, title = "Vingadores: a era de ultron", imageUrl = "url"),
            MovieEntity(movieId = 3, title = "Vingadores: guerra infinita", imageUrl = "url"),
            MovieEntity(movieId = 4, title = "Vingadores: ultimato", imageUrl = "url")
        )

        movieDao.insertMovies(moviesEntities)

        val movies = movieDao.getMovies().first()

        assertThat(movies.size).isEqualTo(4)
        assertThat(movies[0].movieId).isEqualTo(1)
        assertThat(movies[1].movieId).isEqualTo(2)
        assertThat(movies[2].movieId).isEqualTo(3)
        assertThat(movies[3].movieId).isEqualTo(4)
    }

    @Test
    fun returnCorrectMovieById() = runTest {
        val movieEntity = MovieEntity(movieId = 1, title = "Vingadores", imageUrl = "url")

        movieDao.insertMovie(movieEntity)

        val movies = movieDao.getMovies().first()

        val movieClick = movies[0]

        val movieId = movieDao.getMovie(movieClick.movieId)

        assertThat(movieId?.title).isEqualTo(movieClick.title)
    }

    @Test
    fun insertMovies() = runTest {
        val moviesEntities = listOf(
            MovieEntity(movieId = 1, title = "Vingadores", imageUrl = "url"),
            MovieEntity(movieId = 2, title = "Vingadores: a era de ultron", imageUrl = "url"),
            MovieEntity(movieId = 3, title = "Vingadores: guerra infinita", imageUrl = "url"),
            MovieEntity(movieId = 4, title = "Vingadores: ultimato", imageUrl = "url")
        )

        movieDao.insertMovies(moviesEntities)

        val insertedMovies = movieDao.getMovies().first()

        assertThat(moviesEntities.size).isEqualTo(insertedMovies.size)
        assertThat(insertedMovies.containsAll(moviesEntities))
    }

    @Test
    fun insertMovie() = runTest {
        val movieEntity = MovieEntity(movieId = 1, title = "Vingadores", imageUrl = "url")


        movieDao.insertMovie(movieEntity)

        val movies = movieDao.getMovies().first()

        assertThat(movies[0].title).isEqualTo(movieEntity.title)
    }
}