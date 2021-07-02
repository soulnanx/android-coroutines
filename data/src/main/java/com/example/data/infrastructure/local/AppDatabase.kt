package com.example.data.infrastructure.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.infrastructure.local.dao.MoviesDao
import com.example.data.infrastructure.local.entity.MovieEntity

@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}