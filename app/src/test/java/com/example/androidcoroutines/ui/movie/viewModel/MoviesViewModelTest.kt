package com.example.androidcoroutines.ui.movie.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.common.infrastructure.Resource
import com.example.domain.model.Movie
import com.example.domain.usecase.AddMoviesUseCase
import com.example.domain.usecase.GetMoviesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getMoviesUseCase = mockk<GetMoviesUseCase>()
    private val addMovieUseCase = mockk<AddMoviesUseCase>()
    private val moviesLiveData = mockk<Observer<Resource<List<Movie>>>>(relaxed = true)
    private val mockedSuccess = Resource.Success(listOf(Movie(1, "any", 1)))
    private val mockedError = Resource.Error("error", listOf<Movie>())
    private val dispatcher = TestCoroutineDispatcher()

    private fun instantiateViewModel(): MoviesViewModel {
        val viewModel = MoviesViewModel(getMoviesUseCase, addMovieUseCase)
        viewModel.getMovies.observeForever(moviesLiveData)

        return viewModel
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when user instantiate ViewModel then its should call the use case method` () {
        val result: Flow<Resource<List<Movie>>> = flowOf(mockedSuccess)
        coEvery { getMoviesUseCase.invoke() } returns result

        instantiateViewModel()

        coVerify { getMoviesUseCase.invoke() }
    }

    @Test
    fun `when user try to fetch movies data then its returns error` () {
        val result: Flow<Resource<List<Movie>>> = flowOf(mockedError)
        coEvery { getMoviesUseCase.invoke() } returns result

        instantiateViewModel()

        coVerify { moviesLiveData.onChanged(mockedError) }
    }

    @Test
    fun `when user fetches movies data then its returns data` () {
        val result: Flow<Resource<List<Movie>>> = flowOf(mockedSuccess)
        coEvery { getMoviesUseCase.invoke() } returns result

        instantiateViewModel()

        coVerify { moviesLiveData.onChanged(mockedSuccess) }
    }

}