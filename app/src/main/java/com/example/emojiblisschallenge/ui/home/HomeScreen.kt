package com.example.emojiblisschallenge.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onGetEmojiClick: () -> Unit,
    onEmojiListClick: () -> Unit,
    onUsernameChanged: (String) -> Unit,
    onSearchAvatarClick: () -> Unit,
    onAvatarClickList: () -> Unit,
    onGoogleReposClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Emoji Bliss")

        Button(onGetEmojiClick, enabled = !uiState.isLoading) {
            Text(text = "Random Emoji")
        }

        Button(onEmojiListClick) {
            Text(text = "Emoji List")
        }

        uiState.selectedEmoji?.let { emoji ->
            AsyncImage(
                model = emoji.emojiUrl,
                contentDescription = emoji.emojiName,
                modifier = Modifier.size(96.dp)
            )

            Text(text = emoji.emojiName)
        }


        Text(text = "Emojis Loaded: ${uiState.emojis.size}")

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        if (uiState.errorMessage?.isNotEmpty() == true) {
            Text(text = uiState.errorMessage)
        }

        OutlinedTextField(
            value = uiState.usernameInput,
            onValueChange = onUsernameChanged,
            label = { Text("Github Username") },
            singleLine = true,
            enabled = !uiState.isSearchingAvatar
        )

        Button(onClick = onSearchAvatarClick, enabled = !uiState.isSearchingAvatar) {
            Text(text = "Search Avatar")
        }

        when {
            uiState.isSearchingAvatar -> {
                CircularProgressIndicator()
            }

            uiState.avatarErrorMessage != null -> {
                Text(text = uiState.avatarErrorMessage)
            }

            else -> {
                uiState.searchedAvatar?.let { avatar ->
                    AsyncImage(
                        model = avatar.avatarUrl,
                        contentDescription = avatar.userName,
                        modifier = Modifier.size(96.dp)
                    )

                    Text(text = avatar.userName)
                }
            }
        }

        Button(onClick = onAvatarClickList) {
            Text(text = "AvatarList")
        }

        Button(onClick = onGoogleReposClick) {
            Text(text = "Google Repositories")
        }
    }

}