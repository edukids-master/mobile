package itu.m1.edukids.view.quiz

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imagekit.android.ImageKit
import com.imagekit.android.entity.TransformationPosition
import itu.m1.edukids.R
import itu.m1.edukids.model.Reponse

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Reponse>?) {
    val adapter = recyclerView.adapter as QuizGridAdapter
    adapter.submitList(data)
}