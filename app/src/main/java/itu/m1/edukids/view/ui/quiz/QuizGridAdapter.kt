package itu.m1.edukids.view.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import itu.m1.edukids.databinding.QuizCardItemBinding
import itu.m1.edukids.model.Reponse

class QuizGridAdapter : ListAdapter<Reponse, QuizGridAdapter.ReponseViewHolder>(DiffCallback) {
    class ReponseViewHolder(
        private var binding: QuizCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reponse: Reponse) {
            binding.reponse = reponse
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Reponse>() {
        override fun areItemsTheSame(oldItem: Reponse, newItem: Reponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Reponse, newItem: Reponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReponseViewHolder {
        return ReponseViewHolder(QuizCardItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ReponseViewHolder, position: Int) {
        val reponse = getItem(position)
        holder.bind(reponse)
    }
}