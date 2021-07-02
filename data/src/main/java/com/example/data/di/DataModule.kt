package com.example.data.di

import androidx.room.Room
import com.example.data.infrastructure.local.AppDatabase
import com.example.data.infrastructure.service.MoviesService
import com.example.data.infrastructure.service.TasksService
import com.example.data.mapper.MovieMapper
import com.example.data.mapper.TasksMapper
import com.example.data.repository.MoviesRepositoryImpl
import com.example.data.repository.TasksRepositoryImpl
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory<MovieRepository> { MoviesRepositoryImpl( get(), get() ) }
    factory<TasksRepository> { TasksRepositoryImpl( get(), get() ) }
}

val serviceModule = module {
    factory { MoviesService( get(), Dispatchers.Default ) }
    factory { TasksService() }
}

val mapperModule = module {
    single { MovieMapper() }
    single { TasksMapper() }
}

val databaseModule = module {
    single { Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "database-name"
    ).build() }

    single { get<AppDatabase>().movieDao() }
}