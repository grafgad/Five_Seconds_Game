package com.example.fivesecondsgame.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fivesecondsgame.data.repositories.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private val repository = QuestionRepository()
    private var questionNumber = 0

    private val _isTimerVisible = MutableLiveData(false)
    val isTimerVisible: LiveData<Boolean> = _isTimerVisible

    private val _currentQuestion = MutableLiveData("")
    val currentQuestion: LiveData<String> = _currentQuestion

    private val _seconds = MutableLiveData("5")
    val seconds: LiveData<String> = _seconds

    fun onButtonClicked() {
        launch {
            _isTimerVisible.postValue(true)
            val questions = repository.readQuestions()
            _currentQuestion.postValue(questions[questionNumber % questions.size])
            delay(2000)
            for (seconds in 5 downTo 0) {
                _seconds.postValue(seconds.toString())
                delay(1000)
            }
            questionNumber++
            _isTimerVisible.postValue(false)
            _currentQuestion.postValue("Время вышло")
        }
    }
}