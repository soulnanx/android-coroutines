package com.example.data.infrastructure.api

import com.example.data.infrastructure.response.MovieResponse
import com.example.domain.model.Movie
import okhttp3.ResponseBody
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieApi {

    @GET("movies")
    suspend fun getMovies() : List<MovieResponse>

    @POST("movies")
    suspend fun addMovie(@Body movie: Movie): Response<ResponseBody>

}