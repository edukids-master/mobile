package itu.m1.edukids.service

import itu.m1.edukids.model.Activities
import retrofit2.Response
import retrofit2.http.GET

interface ActivitiesService {
    @GET("activities")
    suspend fun getActivities(): Response<List<Activities>>
}