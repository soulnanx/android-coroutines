package com.example.domain.model

data class Movie(
    val id: Int,
    val title: String,
    var score: Int
) {
    fun resetScore() {
        this.score = 0
    }
}
