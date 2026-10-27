package com.example.emojiblisschallenge.data.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EmojiEntity::class,
        AvatarEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)

abstract class AppDatabase : RoomDatabase() {


    abstract fun emojiDao(): EmojiDao

    abstract fun avatarDao(): AvatarDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "emoji_bliss_challenge.db"
                ).build().also { database ->
                    instance = database
                }
            }
        }
    }
}