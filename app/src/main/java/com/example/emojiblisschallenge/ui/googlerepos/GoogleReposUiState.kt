package com.example.emojiblisschallenge.ui.googlerepos

import com.example.emojiblisschallenge.model.GoogleRepo

data class GoogleReposUiState(
    val repos: List<GoogleRepo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val endReached: Boolean = false
)