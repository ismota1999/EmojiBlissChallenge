package com.example.emojiblisschallenge.ui.googlerepos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoogleReposScreen(
    uiState: GoogleReposUiState,
    onLoadMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = uiState.repos,
            key = { repo -> repo.id }
        ) { repo ->

            Column() {
                Text(text = repo.name)
                if (repo.description != null) {
                    Text(text = repo.description)
                }

            }
        }
        item {
            Column() {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    uiState.errorMessage?.let { message ->
                        Text(text = message)
                    }
                }

                if (!uiState.endReached && !uiState.isLoading) {
                    Button(onClick = onLoadMoreClick) {
                        if (uiState.errorMessage != null) {
                            Text(text = "Retry")
                        } else {
                            Text(text = "Load More")
                        }
                    }
                }
            }
        }
    }
}