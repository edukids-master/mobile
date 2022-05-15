package itu.m1.edukids.view.ui.quiz

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import itu.m1.edukids.model.Reponse

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Reponse>?) {
    val adapter = recyclerView.adapter as QuizGridAdapter
    adapter.submitList(data)
}