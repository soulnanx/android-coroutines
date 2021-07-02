package com.example.domain.repository

import com.example.common.infrastructure.Result
import com.example.domain.model.Task

interface TasksRepository {

    suspend fun getTasks(): Result<List<Task>>

}
