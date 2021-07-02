package com.example.data.infrastructure.service

import com.example.data.infrastructure.api.TaskApi
import com.example.data.infrastructure.config.ApiConnection

class TasksService {
    private val api = ApiConnection().create("http://192.168.15.15:3000/", TaskApi::class.java)

    internal suspend fun getAllTasks() = api.getAllTasks()
}
