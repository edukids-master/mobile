package itu.m1.edukids.view.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import itu.m1.edukids.databinding.CardActiviteBinding
import itu.m1.edukids.model.Activities

class HomeAdapter :
    ListAdapter<Activities, HomeAdapter.ActivitiesViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsPhoto] information.
     */
    class ActivitiesViewHolder(
        private var binding: CardActiviteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(activite : Activities) {
            binding.activity = activite
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [MarsPhoto] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Activities>() {
        override fun areItemsTheSame(oldItem: Activities, newItem: Activities): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Activities, newItem: Activities): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivitiesViewHolder {
        return ActivitiesViewHolder(
            CardActiviteBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
