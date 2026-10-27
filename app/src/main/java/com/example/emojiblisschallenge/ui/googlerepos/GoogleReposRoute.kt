package com.example.emojiblisschallenge.ui.googlerepos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GoogleReposRoute(
    modifier: Modifier = Modifier,
    viewModel: GoogleReposViewModel = viewModel(factory = googleReposViewModelFactory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GoogleReposScreen(
        uiState = uiState,
        onLoadMoreClick = viewModel::loadNextPage,
        modifier = modifier
    )
}