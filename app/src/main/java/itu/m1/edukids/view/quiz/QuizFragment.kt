package itu.m1.edukids.view.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import itu.m1.edukids.controller.QuizViewModel
import itu.m1.edukids.databinding.FragmentQuizBinding
import itu.m1.edukids.model.Quiz
import itu.m1.edukids.model.Reponse

class QuizFragment() : Fragment() {
    private val viewModel: QuizViewModel by activityViewModels()
    private var quiz: Quiz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuizBinding.inflate(inflater)

        binding.lifecycleOwner = this
        val quizGridAdapter = QuizGridAdapter() { position ->
            onCardViewClick(position)
        }

        quiz = viewModel.quiz.value

        binding.quiz = quiz
        binding.quizGrid.adapter = quizGridAdapter

        return binding.root
    }

    private fun getSelectedReponse(position: Int): Reponse? {
        if(position == -1) return null

        return quiz?.reponses?.get(position)
    }

    private fun onCardViewClick(position: Int) {
        val reponse = getSelectedReponse(position)
        if (reponse != null) {
            viewModel.selectItem(reponse)
        }
    }
}