package com.example.fivesecondsgame.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//    (tableName = "question_table")
data class QuestionEntity (
    @PrimaryKey
//    @ColumnInfo(name = "question")
    val question: String
)