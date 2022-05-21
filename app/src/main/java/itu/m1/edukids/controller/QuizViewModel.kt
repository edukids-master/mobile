package itu.m1.edukids.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.AppConst
import itu.m1.edukids.model.Quiz
import itu.m1.edukids.model.Reponse
import itu.m1.edukids.service.ApiService
import itu.m1.edukids.service.QuizService
import kotlinx.coroutines.launch

class QuizViewModel : MainViewModel() {
    private val _quizList = MutableLiveData<List<Quiz>>()
    private val quizList: LiveData<List<Quiz>> = _quizList
    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz> = _quiz
    private val _selectedReponse = MutableLiveData<Reponse?>()
    val selectedReponse: LiveData<Reponse?> = _selectedReponse

    fun selectItem(reponse: Reponse?) {
        _selectedReponse.value = reponse
    }

    fun nextQuiz(position: Int) {
        _quiz.value = quizList.value?.get(position)
    }

    fun getQuiz(callback: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiService.quizService.getQuiz(AppConst.QUIZ_COUNT)

                if (response.isSuccessful) {
                    _quizList.value = response.body()
                    _quiz.value = quizList.value?.get(0)
                    callback()
                } else {
                    response.errorBody()?.let { Log.e("ERROR", it.string()) }
                }
            } catch (error: Error) {
                createError("Veuillez nous excusez, une erreur inattendue est survenue")
            }
        }
    }
}