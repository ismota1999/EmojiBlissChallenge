package com.example.emojiblisschallenge.ui.home

import com.example.emojiblisschallenge.model.Avatar
import com.example.emojiblisschallenge.model.Emoji

data class HomeUiState(
    val isLoading: Boolean = false,
    val emojis: List<Emoji> = emptyList(),
    val errorMessage: String? = null,
    val selectedEmoji: Emoji? = null,
    val usernameInput: String = "",
    val isSearchingAvatar: Boolean = false,
    val searchedAvatar: Avatar? = null,
    val avatarErrorMessage: String? = null
    )
