package com.example.emojiblisschallenge.ui.avatarlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun AvatarListScreen(
    uiState: AvatarListUiState,
    onAvatarClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        uiState.errorMessage?.let { message ->
            Text(
                text = message,
                modifier = Modifier.padding(16.dp)
            )
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading && uiState.avatars.isEmpty() -> {
                    CircularProgressIndicator()
                }

                uiState.avatars.isNotEmpty() -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    )
                    {
                        items(
                            items = uiState.avatars,
                            key = { avatars ->
                                avatars.id
                            }
                        ) { avatar ->
                            AsyncImage(
                                model = avatar.avatarUrl,
                                contentDescription = avatar.userName,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable {
                                        onAvatarClick(avatar.id)
                                    }
                                    .padding(8.dp)
                            )
                        }
                    }
                }

                uiState.errorMessage != null -> {
                    Text(text = "No saved avatars yet.")
                }
            }
        }
    }
}