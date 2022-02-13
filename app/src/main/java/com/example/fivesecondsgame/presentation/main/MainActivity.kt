package com.example.fivesecondsgame.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fivesecondsgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter()
        binding.button.setOnClickListener { presenter.onButtonClicked() }
    }

    override fun showQuestion(question: String) {
        binding.timer.visibility = View.VISIBLE
        binding.question.text = question
    }

    override fun timer(seconds: String) {
        binding.timer.text = seconds
    }

    override fun endRound() {
        binding.timer.visibility = View.INVISIBLE
        binding.timer.text = ""
        binding.question.text = "Время вышло"
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }
}