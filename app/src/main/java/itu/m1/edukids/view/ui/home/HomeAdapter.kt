package itu.m1.edukids.view.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
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
    val activiteMap : HashMap<String,Any> = HashMap<String,Any>()

    init{
        activiteMap["quiz"] = QuizActivity::class.java
        activiteMap["nombres"] = MathQuiz::class.java

    }

    class ActivitiesViewHolder(
        private var binding: CardActiviteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val cardView = binding.activityCard
        val cardItemLayout = binding.activityCardItem

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

        val gradientDrawable = GradientDrawable()
        with(gradientDrawable) {
            gradientType = GradientDrawable.LINEAR_GRADIENT
            orientation = GradientDrawable.Orientation.BL_TR
            colors = intArrayOf(
                Color.parseColor(activityCard.colors[0]),
                Color.parseColor(activityCard.colors[1])
            )
        }

        holder.cardItemLayout.background = gradientDrawable

        holder.cardView.setOnClickListener {
            val context = holder.cardView.context
            val intent = Intent(context, activiteMap[activityCard.categorie] as Class<*>)
            context.startActivity(intent)
        }
    }
}
