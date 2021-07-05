package com.example.data.infrastructure.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "url") val url: String?,
)

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(this.id, this.title, this.score, this.url ?: String())
}

fun MovieEntity.toModel(): Movie {
    return Movie(this.id, this.title, this.score, this.url)
}