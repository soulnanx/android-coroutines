package com.example.androidcoroutines.ui.tasks.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.infrastructure.Result
import com.example.domain.model.Movie
import com.example.domain.model.Task
import com.example.domain.usecase.GetTasksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TasksViewModel(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    init {
        performGetTasksUseCase()
    }

    private val _successGetTasks = MutableLiveData<List<Task>>()
    val successGetTasks: LiveData<List<Task>> get() = _successGetTasks

    private val _failureGetTasks = MutableLiveData<String>()
    val failureGetTasks: LiveData<String> get() = _failureGetTasks

    private fun performGetTasksUseCase() {

        viewModelScope.launch {
            getTasksUseCase.invoke().collect { result ->

                when (result) {
                    is Result.Success<List<Task>> -> successGetTasksHandler(result.data)
                    is Result.Error -> failureGetTasksHandler(result.exception)
                }
            }
        }
    }

    private fun failureGetTasksHandler(exception: Exception) {
        _failureGetTasks.value = exception.message
    }

    private fun successGetTasksHandler(data: List<Task>) {
        _successGetTasks.value = data
    }
}