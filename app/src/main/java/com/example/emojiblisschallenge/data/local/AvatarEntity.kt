package com.example.emojiblisschallenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avatars")
data class AvatarEntity(
    @PrimaryKey
    val id: Long,
    val userName: String,
    val avatarUrl: String
)