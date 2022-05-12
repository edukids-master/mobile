package itu.m1.edukids.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.model.Quiz
import itu.m1.edukids.service.ApiService
import itu.m1.edukids.service.QuizService
import kotlinx.coroutines.launch

class QuizViewModel : MainViewModel() {
    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz> = _quiz

    init {
        getQuiz()
    }

    private fun getQuiz() {
        viewModelScope.launch {
            val response = ApiService.quizService.getQuiz()

            if(response.isSuccessful) {
                _quiz.value = response.body()
            } else {
                response.errorBody()?.let { Log.e("ERROR", it.string()) }
            }
        }
    }
}