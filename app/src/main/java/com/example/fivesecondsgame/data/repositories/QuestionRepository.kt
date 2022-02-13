package com.example.fivesecondsgame.data.repositories

import com.example.fivesecondsgame.App
import java.io.BufferedReader
import java.io.InputStreamReader

class QuestionRepository {

    private val context = App.instance

    fun readQuestions(): List<String> {
        val inputStream = context.assets.open("Questions.txt")
        val questions = mutableSetOf<String>()
        val `in` = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        while (true) {
            val line = `in`.readLine() ?: break
            questions.add(line)
        }
        return questions.shuffled()
    }
}