package itu.m1.edukids.view.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import itu.m1.edukids.model.Activities

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Activities>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}
