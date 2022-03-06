package com.example.fivesecondsgame.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fivesecondsgame.data.repositories.QuestionRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel(), CoroutineScope  {

    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private val repository = QuestionRepository()
    private var questionNumber = 0
    private var startQuestionJob: Job? = null

    private val _isTimerVisible = MutableLiveData(false)
    val isTimerVisible: LiveData<Boolean> = _isTimerVisible

    private val _currentQuestion = MutableLiveData("")
    val currentQuestion: LiveData<String> = _currentQuestion

    private val _seconds = MutableLiveData("5")
    val seconds: LiveData<String> = _seconds

    fun onButtonClicked() {
        startQuestionJob?.cancel()
        startQuestionJob = launch {
            _isTimerVisible.postValue(true)
            val questions = repository.myQuestionList()
            val questionEntity = questions[questionNumber % questions.size]
            _currentQuestion.postValue(questionEntity.question)
            _seconds.postValue("5")
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