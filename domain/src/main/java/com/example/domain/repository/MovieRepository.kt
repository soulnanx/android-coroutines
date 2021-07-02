package com.example.domain.repository

import com.example.common.infrastructure.Resource
import com.example.domain.model.Movie
import com.example.common.infrastructure.Result
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    fun fetchMovies(): Flow<Resource<List<Movie>>>
    suspend fun addMovie(movie: Movie)
}