package com.example.fivesecondsgame.presentation.main

interface MainView {

    fun showQuestion(question: String)

    fun timer(seconds: String)

    fun endRound()
}