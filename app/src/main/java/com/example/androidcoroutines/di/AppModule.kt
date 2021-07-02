package com.example.androidcoroutines.di

import com.example.androidcoroutines.ui.movie.viewModel.MoviesViewModel
import com.example.androidcoroutines.ui.tasks.viewModel.TasksViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MoviesViewModel( get(), get()) }
    viewModel { TasksViewModel( get()) }
}