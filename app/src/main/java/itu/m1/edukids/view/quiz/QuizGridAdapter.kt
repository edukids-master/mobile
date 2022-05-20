package itu.m1.edukids.view.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import itu.m1.edukids.R
import itu.m1.edukids.databinding.QuizCardItemBinding
import itu.m1.edukids.model.Reponse

class QuizGridAdapter(val onCardClick: (position: Int) -> Unit) : ListAdapter<Reponse, QuizGridAdapter.ReponseViewHolder>(DiffCallback) {
    var selectedPosition: Int = -1

    inner class ReponseViewHolder(
        private var binding: QuizCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val cardView: CardView = binding.responseCard

        init {
            cardView.setOnClickListener {
                selectedPosition = absoluteAdapterPosition
                onCardClick(selectedPosition)
                notifyDataSetChanged()
            }
        }

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

        holder.cardView.let {
            val backgroundColor = when (position) {
                selectedPosition -> R.color.blue_highlight
                else -> R.color.white
            }

            it.setCardBackgroundColor(ContextCompat.getColor(it.context, backgroundColor))
        }
    }
}