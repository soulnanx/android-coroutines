package com.example.data.infrastructure.api

import com.example.data.infrastructure.response.TaskResponse
import retrofit2.http.GET

interface TaskApi {

    @GET("tasks")
    suspend fun getAllTasks(): List<TaskResponse>

}
