package itu.m1.edukids.view.ui.video

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import itu.m1.edukids.databinding.FragmentVideoCardBinding
import itu.m1.edukids.model.Reponse
import itu.m1.edukids.model.Video
import itu.m1.edukids.view.quiz.QuizGridAdapter

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class VideoListRecyclerViewAdapter(
) : ListAdapter<Video, VideoListRecyclerViewAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentVideoCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = getItem(position)
        holder.bind(video)

        holder.cardView.setOnClickListener {
            val intent = Intent(it.context, VideoDetailActivity::class.java)
            intent.putExtra("videoId", video.videoId)
            intent.putExtra("title", video.title)
            intent.putExtra("description", video.description)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

            it.context.startActivity(intent)
        }
    }

    class ViewHolder(private var binding: FragmentVideoCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cardView: CardView = binding.videoCard

        fun bind(video: Video) {
            binding.video = video
            binding.executePendingBindings()
        }
    }

}