package com.example.domain.usecase


import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import kotlin.random.Random

class AddMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke() {

        val id = Random.nextInt(5, 100)
        val title = Random.nextInt().toString()
        val score = Random.nextInt(0,5)

        val movie = Movie(id, title, score)

        movieRepository.addMovie(movie)
    }
}