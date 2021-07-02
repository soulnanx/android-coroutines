package com.example.androidcoroutines.ui.movie.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.usecase.AddMoviesUseCase
import com.example.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val addMoviesUseCase: AddMoviesUseCase
) : ViewModel() {

    fun addMovie() {
        viewModelScope.launch {
            addMoviesUseCase.invoke()
        }
    }

    val movies = getMoviesUseCase.invoke().asLiveData()



}