package com.example.domain.usecase


import com.example.common.infrastructure.Resource
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class AddMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke(): Flow<Resource<String>> {

        val id = Random.nextInt(5, 100)
        val title = Random.nextInt().toString()
        val score = Random.nextInt(0,5)

        val movie = Movie(id, title, score, null)

        return movieRepository.addMovie(movie)
    }
}