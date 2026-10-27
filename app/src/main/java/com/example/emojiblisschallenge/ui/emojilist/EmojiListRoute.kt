package com.example.emojiblisschallenge.ui.emojilist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EmojiListRoute(
    modifier: Modifier = Modifier,
    viewModel: EmojiListViewModel = viewModel(factory = emojiListViewModelFactory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EmojiListScreen(
        uiState = uiState,
        onEmojiClick = viewModel::removeEmoji,
        onRefresh = viewModel::loadEmojis,
        modifier = modifier
    )

}