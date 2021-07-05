package com.example.data.infrastructure.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("score") val score: Int,
    @SerializedName("url") val url: String,
)