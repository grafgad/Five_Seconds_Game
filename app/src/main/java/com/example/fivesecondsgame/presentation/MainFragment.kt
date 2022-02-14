package com.example.fivesecondsgame.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.fivesecondsgame.databinding.FragmentMainBinding
import com.example.fivesecondsgame.presentation.main.MainPresenter
import com.example.fivesecondsgame.presentation.main.MainView

class MainFragment : BaseFragment<FragmentMainBinding>(), MainView {

    private lateinit var presenter: MainPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = MainPresenter()
        presenter.bind(this)
        binding.questionButton.setOnClickListener { presenter.onButtonClicked() }
    }

    override fun showQuestion(question: String) {
        binding.question.text = question
    }

    override fun timer(seconds: String) {
        with(binding) {
            timer.text = seconds
            timer.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            progressBar.max = 5
            progressBar.progress = seconds.toInt()
        }
    }

    override fun endRound() {
        with(binding) {
            progressBar.visibility = View.INVISIBLE
            timer.visibility = View.INVISIBLE
            question.text = "Время вышло"
        }
    }

    override fun getViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

    override fun onDestroyView() {
        presenter.unbind()
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}