package itu.m1.edukids.service

import com.google.android.youtube.player.YouTubeApiServiceUtil
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://edukids-api.herokuapp.com/api/"

//private const val BASE_URL = "https://4237-154-126-56-74.ngrok.io/api/"
private const val YOUTUBE_API = "https://youtube.googleapis.com/youtube/v3/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private val youtubeRetrofitBuilder = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(YOUTUBE_API)
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ApiService {
    val userService: UserService by lazy { retrofit.create(UserService::class.java) }
    val activitiesService: ActivitiesService by lazy { retrofit.create(ActivitiesService::class.java) }
    val quizService: QuizService by lazy { retrofit.create(QuizService::class.java) }
    val videoService: VideoService by lazy { youtubeRetrofitBuilder.create(VideoService::class.java) }
}