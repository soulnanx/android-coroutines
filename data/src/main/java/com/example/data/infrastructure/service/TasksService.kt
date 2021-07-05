package com.example.data.infrastructure.service

import com.example.data.BuildConfig
import com.example.data.infrastructure.api.TaskApi
import com.example.data.infrastructure.config.ApiConnection

class TasksService {
    private val api = ApiConnection().create(BuildConfig.REST_ENDPOINT, TaskApi::class.java)

    internal suspend fun getAllTasks() = api.getAllTasks()
}
