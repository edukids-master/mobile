package itu.m1.edukids.view.ui.video

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imagekit.android.ImageKit
import com.imagekit.android.entity.TransformationPosition
import itu.m1.edukids.R
import itu.m1.edukids.model.Video

@BindingAdapter("videoList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Video>?) {
    val adapter = recyclerView.adapter as VideoListRecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("videoImg")
fun bindImage(imgView: ImageView, imgUrl: String) {
    val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
    imgView.load(imgUri) {
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}