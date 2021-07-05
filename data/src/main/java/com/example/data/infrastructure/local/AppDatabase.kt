package com.example.data.infrastructure.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.infrastructure.local.dao.MoviesDao
import com.example.data.infrastructure.local.entity.MovieEntity


@Database(entities = arrayOf(MovieEntity::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}

class DatabaseFactory() {

    private val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Movie ADD COLUMN url TEXT")
        }
    }

    fun getDatabase(context: Context) =
        Room
            .databaseBuilder(context, AppDatabase::class.java, "database-name")
            .addMigrations(migration_1_2).build()
}