package com.example.emojiblisschallenge.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.emojiblisschallenge.model.Emoji

@Dao
interface EmojiDao {

    @Query("SELECT * FROM emojis ORDER BY emojiName")
    suspend fun getAll(): List<EmojiEntity>

    @Upsert
    suspend fun saveAll(emojis: List<EmojiEntity>)
}