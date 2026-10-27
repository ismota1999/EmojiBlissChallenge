package com.example.emojiblisschallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emojiblisschallenge.ui.avatarlist.AvatarListRoute
import com.example.emojiblisschallenge.ui.emojilist.EmojiListRoute
import com.example.emojiblisschallenge.ui.emojilist.EmojiListScreen
import com.example.emojiblisschallenge.ui.googlerepos.GoogleReposRoute
import com.example.emojiblisschallenge.ui.home.HomeRoute

@Composable
fun EmojiBlissApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable(route = "home") {
            HomeRoute(
                onEmojiListClick = {
                    navController.navigate("emoji_list")
                },
                onAvatarClickList = {
                    navController.navigate("avatar_list")
                },
                onGoogleReposClick = {
                    navController.navigate("google_repos")
                }
            )
        }

        composable(route = "emoji_list") {
            EmojiListRoute()
        }

        composable(route = "avatar_list") {
            AvatarListRoute()
        }

        composable(route = "google_repos") {
            GoogleReposRoute()
        }
    }

}