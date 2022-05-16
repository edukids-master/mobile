package itu.m1.edukids.view.ui.home

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import itu.m1.edukids.databinding.CardActiviteBinding
import itu.m1.edukids.model.Activities
import itu.m1.edukids.view.GameListActivity
import itu.m1.edukids.view.quiz.QuizActivity
import itu.m1.edukids.view.ui.math.MathQuiz

class HomeAdapter :
    ListAdapter<Activities, HomeAdapter.ActivitiesViewHolder>(DiffCallback) {
    val activiteMap : HashMap<String,Any>

    init{
        activiteMap = HashMap<String,Any>()
        activiteMap.put("quiz", QuizActivity::class.java)
        activiteMap.put("nombres", MathQuiz::class.java)

    }

    class ActivitiesViewHolder(
        private var binding: CardActiviteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val cardView = binding.activityCard
        fun bind(activite : Activities) {
            binding.activity = activite
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Activities>() {
        override fun areItemsTheSame(oldItem: Activities, newItem: Activities): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Activities, newItem: Activities): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivitiesViewHolder {
        return ActivitiesViewHolder(
            CardActiviteBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val activityCard = getItem(position)
        holder.bind(activityCard)
        holder.cardView.setOnClickListener {
            val context = holder.cardView.context
            val intent = Intent(context, activiteMap.get(activityCard.categorie) as Class<*>)
            context.startActivity(intent)
        }
    }
}
