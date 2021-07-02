package com.example.domain.usecase


import com.example.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    fun invoke() = movieRepository.fetchMovies()
}