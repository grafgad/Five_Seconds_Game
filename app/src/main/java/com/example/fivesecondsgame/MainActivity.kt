package com.example.fivesecondsgame

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fivesecondsgame.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showQuestion()
    }

    private fun showQuestion() {
        val questions = readQuestions()
        binding.button.setOnClickListener {
            binding.timer.visibility = View.VISIBLE
            binding.question.text = questions.random()
            thread {
                Thread.sleep(2000)
                for (i in 5 downTo 0) {
                    Thread.sleep(1000)
                    binding.root.post {
                        binding.timer.text = i.toString()
                    }
                }
                binding.root.post {
                    binding.timer.visibility = View.INVISIBLE
                    binding.timer.text = ""
                    binding.question.text = "46546"
                }
            }
        }
    }

    private fun readQuestions(): List<String> {
        val inputStream = assets.open("Questions.txt")
        val questions = mutableSetOf<String>()
        val `in` = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        while (true) {
            val line = `in`.readLine() ?: break
            questions.add(line)
        }
        return questions.shuffled()
    }
}