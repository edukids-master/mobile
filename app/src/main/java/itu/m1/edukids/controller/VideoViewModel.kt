package itu.m1.edukids.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.AppConst
import itu.m1.edukids.model.Video
import itu.m1.edukids.service.ApiService
import kotlinx.coroutines.launch

class VideoViewModel : MainViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> = _videos

    init {
        getVideos()
    }

    private fun getValue(obj: Any?, field: String): Any? {
        return (obj as Map<String, Any>)[field]
    }

    private fun getValue(obj: Any?, vararg fields: String): Any? {
        var value: Any? = obj

        fields.forEach {
            value = getValue(value, it)

            if(value is String) return value
        }

        return value
    }

    private fun formatText(text: String): String {
        return text.replace("&#39;", "'")
    }

    private fun parseResult(content: Map<String, Any>?) {
        content?.let {
            val videos = mutableListOf<Video>()
            val items = it["items"] as List<Map<String, Any>>

            items.forEach { item ->
//                val videoId = getValue(getValue(item, "id"), "videoId")
                val videoId = getValue(item, "id", "videoId")

                if(videoId != null) {
                    val snippet = getValue(item, "snippet")
                    val title = formatText(getValue(snippet, "title") as String)
                    val description = getValue(snippet, "description") as String
                    val url = getValue(snippet, "thumbnails", "medium", "url") as String

                    videos += Video(videoId as String, title, description, url)
                }
            }

            _videos.value = videos
        }
    }

    private fun getVideos() {
        viewModelScope.launch {
            try {
                val filter = mutableMapOf<String, String>()
                filter["channelId"] = AppConst.YOUTUBE_CHANNEL_ID
                filter["part"] = "snippet"
                filter["maxResults"] = "20"

                val response = ApiService.videoService.getVideos(AppConst.YOUTUBE_API_KEY, filter)

                if (response.isSuccessful) {
                    parseResult(response.body() as Map<String, Any>)
                } else {
                    response.errorBody()?.let { Log.e("ERROR", it.string()) }
                }
            } catch(error: Error) {
                createError("Veuillez nous excusez, une erreur inattendue est survenue")
            }
        }
    }
}