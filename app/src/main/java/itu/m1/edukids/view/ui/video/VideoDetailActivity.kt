package itu.m1.edukids.view.ui.video

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.youtube.player.FragmentVideoPlayer
import com.google.android.youtube.player.YouTubeApiServiceUtil
import com.google.android.youtube.player.YouTubeInitializationResult
import itu.m1.edukids.R
import itu.m1.edukids.databinding.ActivityVideoDetailActivityBinding
import itu.m1.edukids.model.Video

class VideoDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideoDetailActivityBinding
    lateinit var videoTitle: TextView
    lateinit var videoDesc: TextView
    lateinit var toolbar: Toolbar

    lateinit var video: Video

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoTitle = binding.tvTitle
        videoDesc = binding.tvDesc
        toolbar = binding.toolbar

        getData()
        playVideo()
    }

    private fun playVideo() {
        val result: YouTubeInitializationResult =
            YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this)
        if (result != YouTubeInitializationResult.SUCCESS) {
            result.getErrorDialog(this, 0).show()
            return
        }
        val videoPlayerFragment: FragmentVideoPlayer =
            supportFragmentManager.findFragmentById(R.id.youtube_fragment) as FragmentVideoPlayer
        videoPlayerFragment.videoId = video.videoId
    }

    private fun getData() {
        val intent = intent
        video = Video(
            intent.getStringExtra("videoId").toString(),
            intent.getStringExtra("title").toString(),
            intent.getStringExtra("description").toString()
        )

        with(video) {
            videoTitle.text = title
            videoDesc.text = description
            toolbar.title = title

            setSupportActionBar(toolbar)
        }
    }
}