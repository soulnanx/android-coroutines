package com.example.data.repository

import com.example.common.infrastructure.Resource
import com.example.data.infrastructure.local.dao.MoviesDao
import com.example.data.infrastructure.local.entity.toEntity
import com.example.data.infrastructure.local.entity.toModel
import com.example.data.infrastructure.networkBoundResource
import com.example.data.infrastructure.service.MoviesService
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class MoviesRepositoryImpl(
    private val moviesService: MoviesService,
    private val moviesDao: MoviesDao
) : MovieRepository {

    override fun fetchMovies() : Flow<Resource<List<Movie>>> = networkBoundResource(
        query = {
            moviesDao.getAll().map { moviesEntity ->
                moviesEntity.map { movieEntity -> movieEntity.toModel() }
            }
        },
        fetch = {
            moviesService.getMovies()
        },
        saveFetchResult = { movies ->
            moviesDao.deleteAll()
            val moviesEntity = movies.map { movie -> movie.toEntity() }
            moviesDao.insertAll(*moviesEntity.toTypedArray())
        },
        shouldFetch = {
            true
        }

    )

    override suspend fun addMovie(movie: Movie) : Flow<Resource<String>> {
        return flow {
            try {
                emit(Resource.Loading())
                moviesService.addMovie(movie)
                moviesDao.insertAll(movie.toEntity())
                emit(Resource.Success(""))
            } catch (e: Exception){
                emit(Resource.Error<String>("Unable to connect!"))
            }
        }
    }
}