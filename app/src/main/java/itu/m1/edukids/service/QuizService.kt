package itu.m1.edukids.service

import itu.m1.edukids.model.Quiz
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {
    @GET("quiz/generate")
    suspend fun getQuiz(@Query("count") count: Int): Response<List<Quiz>>
}