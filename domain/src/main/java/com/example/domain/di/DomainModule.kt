package com.example.domain.di

import com.example.domain.usecase.AddMoviesUseCase
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.usecase.GetTasksUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory { GetMoviesUseCase( get() ) }
    factory { AddMoviesUseCase( get() ) }
    factory { GetTasksUseCase( get() ) }
}
