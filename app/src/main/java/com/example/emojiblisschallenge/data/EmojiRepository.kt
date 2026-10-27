package com.example.emojiblisschallenge.data

import com.example.emojiblisschallenge.data.local.EmojiDao
import com.example.emojiblisschallenge.data.local.EmojiEntity
import com.example.emojiblisschallenge.model.Emoji

class EmojiRepository(
    private val api: GithubApi,
    private val emojiDao: EmojiDao
) {

    suspend fun getEmojis(): List<Emoji> {
        val cachedEmojis = emojiDao.getAll()

        if (cachedEmojis.isNotEmpty()) {
            return cachedEmojis.map { entity ->
                Emoji(
                    emojiName = entity.emojiName,
                    emojiUrl = entity.emojiUrl
                )
            }
        }

        val response = api.getEmojis()

        val entities = response.map { entry->
            EmojiEntity(
                emojiName = entry.key,
                emojiUrl = entry.value
            )
        }
        emojiDao.saveAll(entities)

        return entities.map { entity ->
            Emoji(
                emojiName = entity.emojiName,
                emojiUrl = entity.emojiUrl
            )
        }
    }

}