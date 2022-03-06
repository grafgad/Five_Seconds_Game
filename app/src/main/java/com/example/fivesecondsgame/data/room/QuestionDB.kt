package com.example.fivesecondsgame.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        QuestionEntity::class
    ],
    version = 1
)
abstract class QuestionDB: RoomDatabase() {
    abstract fun questionDao(): QuestionDAO
}