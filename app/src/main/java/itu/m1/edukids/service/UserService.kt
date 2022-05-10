package itu.m1.edukids.service

import itu.m1.edukids.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user/login")
    suspend fun connexion(@Body user: User): User
}