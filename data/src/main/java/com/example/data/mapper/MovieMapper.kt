package com.example.data.mapper

import com.example.common.mapper.DataMapper
import com.example.data.infrastructure.response.MovieResponse
import com.example.domain.model.Movie

class MovieMapper: DataMapper<List<MovieResponse>, List<Movie>> {
    override fun from(source: List<MovieResponse>) =
        source.map { response ->
            Movie(
                id = response.id,
                title = response.title,
                score = response.score
            )
        }
}