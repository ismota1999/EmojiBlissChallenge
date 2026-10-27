package com.example.emojiblisschallenge.data.remote

import com.google.gson.annotations.SerializedName

data class GithubRepoResponse (
    val id: Long,
    val name: String,
    val description: String?,
    @SerializedName("html_url")
    val htmlUrl: String
)