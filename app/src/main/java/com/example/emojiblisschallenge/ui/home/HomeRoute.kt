package com.example.emojiblisschallenge.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    onEmojiListClick: () -> Unit,
    onAvatarClickList: () -> Unit,
    onGoogleReposClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = homeViewModelFactory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onGetEmojiClick = viewModel::loadEmojis,
        onEmojiListClick = onEmojiListClick,
        onUsernameChanged = viewModel::onUsernameChanged,
        onSearchAvatarClick = viewModel::searchAvatar,
        onAvatarClickList = onAvatarClickList,
        onGoogleReposClick = onGoogleReposClick,
        modifier = modifier
    )


}