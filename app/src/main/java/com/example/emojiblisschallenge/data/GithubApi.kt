package com.example.emojiblisschallenge.data

import com.example.emojiblisschallenge.data.remote.GithubRepoResponse
import com.example.emojiblisschallenge.data.remote.GithubUserResponse
import com.example.emojiblisschallenge.model.Emoji
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {


    @GET("emojis")
    suspend fun getEmojis(): Map<String, String>

    @GET("users/{username}")
    suspend fun getUser(
        @Path ("username") username: String
    ): GithubUserResponse

    @GET("users/google/repos")
    suspend fun getGoogleRepos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<GithubRepoResponse>
}