package com.example.emojiblisschallenge.ui.emojilist

import com.example.emojiblisschallenge.model.Emoji

data class EmojiListUiState (
    val isLoading: Boolean = false,
    val emojis: List<Emoji> = emptyList(),
    val errorMessage: String? = null,
    val loadVersion: Int = 0
)