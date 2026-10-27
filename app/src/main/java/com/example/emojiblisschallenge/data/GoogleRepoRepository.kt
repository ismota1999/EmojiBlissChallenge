package com.example.emojiblisschallenge.data

import com.example.emojiblisschallenge.model.GoogleRepo

class GoogleRepoRepository(
    private val api: GithubApi
) {
    suspend fun getRepos (
        page: Int,
        perPage: Int
    ) : List<GoogleRepo> {

        val response = api.getGoogleRepos(page = page, perPage = perPage)

        val googleRepoList = response.map { entry ->
            GoogleRepo(
                id = entry.id,
                name = entry.name,
                description = entry.description,
                htmlUrl = entry.htmlUrl
            )
        }
        return googleRepoList
    }
}