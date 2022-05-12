package itu.m1.edukids.view.ui.home

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imagekit.android.ImageKit
import com.imagekit.android.entity.TransformationPosition
import itu.m1.edukids.R
import itu.m1.edukids.model.Activities
import kotlin.math.max

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Activities>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView : ImageView, imgUrl : String){
    val image = ImageKit.getInstance()
        .url(
            path = imgUrl,
            transformationPosition = TransformationPosition.QUERY
        ).height(300).width(300)
        .aspectRatio(3, 3)
        .create()

        image?.let {
            val imgUri = image.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
}