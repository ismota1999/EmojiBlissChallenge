package com.example.emojiblisschallenge.ui.avatarlist

import com.example.emojiblisschallenge.model.Avatar

data class AvatarListUiState(
    val isLoading: Boolean = false,
    val avatars: List<Avatar> = emptyList(),
    val errorMessage: String? = null
)