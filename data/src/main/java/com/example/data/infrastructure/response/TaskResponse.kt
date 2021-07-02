package com.example.data.infrastructure.response

import com.google.gson.annotations.SerializedName

class TaskResponse(
    @SerializedName("title") val title: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("completed") val completed: Boolean,
)
