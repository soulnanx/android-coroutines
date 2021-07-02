package com.example.data.infrastructure.service

import com.example.data.infrastructure.api.MovieApi
import com.example.data.infrastructure.config.ApiConnection
import com.example.data.infrastructure.response.MovieResponse
import com.example.data.mapper.MovieMapper
import com.example.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.common.infrastructure.Result
import kotlinx.coroutines.CoroutineDispatcher

class MoviesService(
    private val movieMapper: MovieMapper,
    private val dispatcher: CoroutineDispatcher
) {
    private val api = ApiConnection().create("http://192.168.15.15:3000/", MovieApi::class.java)

    internal suspend fun getMovies(): List<Movie> {
        return withContext(dispatcher) {
            try {
                val result: List<MovieResponse> = api.getMovies()
                val parsed: List<Movie> = movieMapper.from(result)
                parsed
            } catch (e: Exception) {
                throw Exception("Unable to connect!")
            }
        }

    }

    internal suspend fun addMovie(movie: Movie) {
        return withContext(dispatcher) {
            try {
                val result = api.addMovie(movie)
                result.toString()
            } catch (e: Exception) {
                throw Exception("Unable to connect!")
            }
        }

    }
}