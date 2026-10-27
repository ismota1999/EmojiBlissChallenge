package com.example.emojiblisschallenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emojis")
data class EmojiEntity(
    @PrimaryKey
    val emojiName: String,
    val emojiUrl: String
)