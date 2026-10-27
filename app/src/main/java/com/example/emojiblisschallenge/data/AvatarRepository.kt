package com.example.emojiblisschallenge.data

import com.example.emojiblisschallenge.data.local.AvatarDao
import com.example.emojiblisschallenge.data.local.AvatarEntity
import com.example.emojiblisschallenge.model.Avatar

class AvatarRepository(
    private val api: GithubApi,
    private val avatarDao: AvatarDao
) {
    suspend fun getAvatar(username: String): Avatar {
        val cleanUsername = username.trim()
        require(cleanUsername.isNotEmpty()) {
            "Username cannot be empty!"
        }

        val cachedAvatar = avatarDao.getByUsername(cleanUsername)

        if (cachedAvatar != null) {
            return Avatar(
                id = cachedAvatar.id,
                userName = cachedAvatar.userName,
                avatarUrl = cachedAvatar.avatarUrl
            )
        }

        val response = api.getUser(username = cleanUsername)

        val entity: AvatarEntity = AvatarEntity(
            id = response.id,
            userName = response.login,
            avatarUrl = response.avatarUrl
        )

        avatarDao.save(entity)

        return Avatar(
            entity.id,
            entity.userName,
            entity.avatarUrl
        )
    }

    suspend fun getSavedAvatars(): List<Avatar> {
        val cachedAvatars = avatarDao.getAll()

        return cachedAvatars.map { entity ->
            Avatar(
                id = entity.id,
                userName = entity.userName,
                avatarUrl = entity.avatarUrl
            )
        }
    }

    suspend fun deleteAvatar(avatarId: Long) {
        avatarDao.deleteById(avatarId)
    }
}


