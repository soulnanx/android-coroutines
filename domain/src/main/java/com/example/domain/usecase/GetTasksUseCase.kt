package com.example.domain.usecase

import com.example.common.infrastructure.Result
import com.example.domain.model.Task
import com.example.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTasksUseCase(
    private val tasksRepository: TasksRepository
) {

    suspend fun invoke(): Flow<Result<List<Task>>> {
        return flow {
           val result = tasksRepository.getTasks()

           emit(result)

        }



    }

}
