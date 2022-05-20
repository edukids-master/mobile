package com.google.android.youtube.player

import android.os.Bundle
import itu.m1.edukids.AppConst

class FragmentVideoPlayer : YouTubePlayerSupportFragmentX(), YouTubePlayer.OnInitializedListener {

    var videoId: String = ""
        set(value) {
            field = value
            initialize(AppConst.YOUTUBE_API_KEY, this)
        }

//    private var onVideoPlayListener: OnVideoPlayListener? = null
//
//    interface OnVideoPlayListener {
//        fun onPlaying(videoId: String)
//    }

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)

        val bundle: Bundle? = arguments

        p0?.let {
            if(it.containsKey("KEY_VIDEO_ID")) {
                videoId = it.getString("KEY_VIDEO_ID").toString()
            } else {
                if (bundle != null) {
                    videoId = bundle.getString("KEY_VIDEO_ID").toString()
                }
            }
        }
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if(!videoId.isNullOrEmpty()) {
            p1?.loadVideo(videoId)

            if(p2) {
                p1?.play()
            }
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        if(p1?.isUserRecoverableError == true) {
            p1.getErrorDialog(activity, 1).show()
        }
    }

    override fun onSaveInstanceState(p0: Bundle) {
        super.onSaveInstanceState(p0)
        p0.putString("KEY_VIDEO_ID", videoId)
    }
}