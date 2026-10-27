package com.example.emojiblisschallenge.data.remote

import com.google.gson.annotations.SerializedName

data class GithubUserResponse(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)