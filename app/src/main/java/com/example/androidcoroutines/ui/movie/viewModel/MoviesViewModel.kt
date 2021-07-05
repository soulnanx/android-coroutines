package com.example.androidcoroutines.ui.movie.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.infrastructure.Resource
import com.example.domain.model.Task
import com.example.domain.usecase.AddMoviesUseCase
import com.example.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesViewModel(
    getMoviesUseCase: GetMoviesUseCase,
    private val addMoviesUseCase: AddMoviesUseCase
) : ViewModel() {

    val getMovies = getMoviesUseCase.invoke().asLiveData()

    private val _postMovie = MutableLiveData<Resource<String>>()
    val postMovie: LiveData<Resource<String>> get() = _postMovie

    fun addMovie() {
        viewModelScope.launch {
            addMoviesUseCase.invoke().collect {
                _postMovie.value = it
            }
        }
    }





}