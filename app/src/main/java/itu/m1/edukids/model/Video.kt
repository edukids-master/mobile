package itu.m1.edukids.model

data class Video(
    val videoId: String,
    val title: String,
    val description: String,
    val url: String = ""
)
