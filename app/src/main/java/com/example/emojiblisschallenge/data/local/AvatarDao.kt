package com.example.emojiblisschallenge.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface AvatarDao {

    @Query("SELECT * FROM avatars ORDER BY userName")
    suspend fun getAll(): List<AvatarEntity>

    @Query("SELECT * FROM avatars WHERE userName = :username COLLATE NOCASE LIMIT 1")
    suspend fun getByUsername(username: String): AvatarEntity?

    @Query("DELETE FROM avatars WHERE id = :avatarId")
    suspend fun deleteById(avatarId: Long)

    @Upsert
    suspend fun save(avatar: AvatarEntity)
}