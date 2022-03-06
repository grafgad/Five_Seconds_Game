package com.example.fivesecondsgame.presentation.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fivesecondsgame.databinding.FragmentMainBinding
import com.example.fivesecondsgame.presentation.BaseFragment
import com.example.fivesecondsgame.presentation.visibleIf

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.isTimerVisible.observe(viewLifecycleOwner, ::updateTimerVisibility)
        viewModel.currentQuestion.observe(viewLifecycleOwner, ::showQuestion)
//        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer { showQuestion(it) })
        viewModel.seconds.observe(viewLifecycleOwner, ::timer)

        binding.progressBar.max = 5
        binding.questionButton.setOnClickListener {
            viewModel.onButtonClicked()
        }
    }

    private fun updateTimerVisibility(visible: Boolean) {
        with(binding) {
            timer.visibleIf(visible)
            progressBar.visibleIf(visible)
        }
    }

    private fun showQuestion(question: String) {
        binding.question.text = question
    }

    private fun timer(seconds: String) {
        with(binding) {
            timer.text = seconds
            progressBar.progress = seconds.toInt()
        }
    }

    override fun getViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

    companion object {
        fun newInstance() = MainFragment()
    }
}

