package com.example.data.infrastructure.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.infrastructure.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE title LIKE :title LIMIT 1")
    fun findByName(title: String): Flow<MovieEntity>

    @Query("DELETE FROM movie")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movies: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

}