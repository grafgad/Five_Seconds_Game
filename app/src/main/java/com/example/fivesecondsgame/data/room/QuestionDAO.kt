package com.example.fivesecondsgame.data.room

import androidx.room.*

@Dao
interface QuestionDAO {
    @Query("SELECT * FROM questionentity")
    fun getAll(): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( questions: List<QuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: QuestionEntity)

    @Delete
    fun delete (question: QuestionEntity)
}