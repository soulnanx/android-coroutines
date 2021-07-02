package com.example.data.repository

import com.example.data.infrastructure.service.TasksService
import com.example.data.mapper.TasksMapper
import com.example.domain.model.Task
import com.example.domain.repository.TasksRepository
import com.example.common.infrastructure.Result
import com.example.data.infrastructure.response.TaskResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

class TasksRepositoryImpl(
    private val tasksService: TasksService,
    private val tasksMapper: TasksMapper,
) : TasksRepository {

    override suspend fun getTasks(): Result<List<Task>> {
        return withContext(Dispatchers.Default) {
            try {
                val result = tasksService.getAllTasks()
                val parsed = tasksMapper.from(result)
                Result.Success(parsed)
            } catch (e: Exception){
                Result.Error(Exception("Unable to connect"))
            }
        }
    }

}