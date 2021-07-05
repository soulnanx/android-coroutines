package com.example.data.repository


import com.example.data.infrastructure.local.dao.MoviesDao
import com.example.data.infrastructure.local.entity.MovieEntity
import com.example.data.infrastructure.local.entity.toEntity
import com.example.data.infrastructure.service.MoviesService
import com.example.domain.model.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class MoviesRepositoryImplTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val service = mockk<MoviesService>()
    private val dao = mockk<MoviesDao>()
    private val repository = MoviesRepositoryImpl(service, dao)

    private val movie = Movie(1, "any", 1)
    private val movieList = listOf<Movie>(movie)
    private val emptyMovieEntityList = listOf<MovieEntity>()
    private val movieEntityList = listOf( movie.toEntity())

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get movies without local cache`() = runBlocking {

        val arrayItem = movieList.map {
            it.toEntity()
        }.toTypedArray()

        coEvery { service.getMovies() } returns movieList
        coEvery { dao.getAll() } returns flowOf(emptyMovieEntityList) andThen {
            flowOf(movieEntityList)
        }
        coEvery { dao.deleteAll() } returns Unit
        coEvery { dao.insertAll(*arrayItem) } returns Unit
        val moviesFlow = repository.fetchMovies()

        val loading = moviesFlow.first()
        moviesFlow.drop(1)
        assert(loading.data == emptyMovieEntityList)

        val success = moviesFlow.first()
        moviesFlow.drop(1)
        assert(success.data!!.size == movieList.size)
    }

    @Test
    fun `get movies with local cache`() = runBlocking {
        coEvery { dao.getAll() } returns flowOf(movieEntityList)

        val loading = repository.fetchMovies().first()

        assert(loading.data == movieList)
    }

}