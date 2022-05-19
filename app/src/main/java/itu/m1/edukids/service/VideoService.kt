package itu.m1.edukids.service

import itu.m1.edukids.model.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface VideoService {
    @GET("search")
    suspend fun getVideos(@Query("key") key: String, @QueryMap filter: Map<String, String> = mapOf()): Response<Any>
}