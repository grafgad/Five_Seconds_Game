package com.example.fivesecondsgame.data.repositories

import com.example.fivesecondsgame.App
import com.example.fivesecondsgame.data.room.QuestionEntity
import java.io.BufferedReader
import java.io.InputStreamReader


class QuestionRepository {

    private val context = App.instance
    private val questionDAO = App.db.questionDao()

    private fun readQuestions(): List<String> {
        val inputStream = context.assets.open("Questions.txt")
        val `in` = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        val questions = mutableSetOf<String>()
        while (true) {
            val line = `in`.readLine() ?: break
            questions.add(line)
        }
        return questions.shuffled()
    }

    private fun mapStringToQuestionEntity(strings: List<String>): List<QuestionEntity> {
        return strings.map {
            QuestionEntity(it)
        }
    }

    fun myQuestionList(): List<QuestionEntity> {
        val questions = readQuestions()
        val questionEntities = mapStringToQuestionEntity(questions)
        questionDAO.insertAll(questionEntities)
        return questionDAO.getAll()
    }
}