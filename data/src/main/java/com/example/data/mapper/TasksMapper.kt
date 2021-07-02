package com.example.data.mapper

import com.example.common.mapper.DataMapper
import com.example.data.infrastructure.response.TaskResponse
import com.example.domain.model.Task

class TasksMapper : DataMapper<List<TaskResponse>, List<Task>> {
    override fun from(source: List<TaskResponse>) =
        source.map { response ->
            Task(
                title = response.title,
                duration = response.duration,
                completed = response.completed,
            )
        }

}
