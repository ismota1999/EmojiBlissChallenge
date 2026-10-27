package com.example.emojiblisschallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.emojiblisschallenge.ui.EmojiBlissApp
import com.example.emojiblisschallenge.ui.home.HomeRoute
import com.example.emojiblisschallenge.ui.theme.EmojiBlissChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmojiBlissChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EmojiBlissApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}