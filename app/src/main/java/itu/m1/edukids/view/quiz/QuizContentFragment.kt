package itu.m1.edukids.view.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import itu.m1.edukids.R
import itu.m1.edukids.controller.QuizViewModel
import itu.m1.edukids.databinding.FragmentNotificationsBinding
import itu.m1.edukids.databinding.FragmentQuizContentBinding
import itu.m1.edukids.model.Quiz

/**
 * A simple [Fragment] subclass.
 * Use the [QuizContent.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizContentFragment() : Fragment() {
    private var _binding: FragmentQuizContentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by activityViewModels()

    private lateinit var responseListFragment: QuizFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizContentBinding.inflate(inflater, container, false)

//        viewModel.quiz.observe(viewLifecycleOwner, Observer {
//            getData(it)
//        })

        viewModel.quiz.value?.let { getData(it) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        attachFragment()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun attachFragment() {
        responseListFragment = QuizFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(
            R.id.quiz_response_container,
            responseListFragment,
            "responseListFragment"
        ).commit()
    }

    private fun getData(quiz: Quiz) {
        with(quiz) {
            binding.quizTitle.text = question.text
            binding.quizDescription.text = indice.desc
        }
    }
}