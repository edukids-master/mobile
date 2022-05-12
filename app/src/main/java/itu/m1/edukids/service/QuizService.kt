package itu.m1.edukids.service

import itu.m1.edukids.model.Quiz
import retrofit2.Response
import retrofit2.http.GET

interface QuizService {
    @GET("quiz")
    suspend fun getQuiz(): Response<Quiz>
}