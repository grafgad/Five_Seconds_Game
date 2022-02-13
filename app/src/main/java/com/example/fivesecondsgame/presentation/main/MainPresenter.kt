package com.example.fivesecondsgame.presentation.main

import com.example.fivesecondsgame.data.repositories.QuestionRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private var view: MainView? = null
    private val repository = QuestionRepository()
    private var questionNumber = 0

    fun bind(activity: MainActivity) {
        this.view = activity
    }

    fun unbind() {
        cancel()
        view = null
    }

    fun onButtonClicked() {
        launch {
            val questions = repository.readQuestions()
            withContext(Dispatchers.Main) {
                view?.showQuestion(questions[questionNumber % questions.size])
            }
            delay(2000)
            for (seconds in 5 downTo 0) {
                withContext(Dispatchers.Main) {
                    view?.timer(seconds.toString())
                }
                delay(1000)
            }
            questionNumber++
            withContext(Dispatchers.Main) {
                view?.endRound()
            }
        }
    }
}