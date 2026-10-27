package com.example.emojiblisschallenge.ui.avatarlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AvatarListRoute(
    modifier: Modifier = Modifier,
    viewModel: AvatarListViewModel = viewModel(factory = avatarListViewModelFactory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AvatarListScreen(
        uiState = uiState,
        onAvatarClick = viewModel::deleteAvatar,
        modifier = modifier
    )
}