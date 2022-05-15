package itu.m1.edukids.view.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import itu.m1.edukids.controller.QuizViewModel
import itu.m1.edukids.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuizBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.quizGrid.adapter = QuizGridAdapter()

        return binding.root
    }

}