package itu.m1.edukids.view.ui.math

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import itu.m1.edukids.R

import itu.m1.edukids.databinding.FragmentMathResponseBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MathResponseAdapter(
    private val values: List<Int>,
    val lambda: (position: Int) -> Unit
) : RecyclerView.Adapter<MathResponseAdapter.ViewHolder>() {
    var selectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMathResponseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textView.text = item.toString()

        holder.cardView.let {
            if(position != selectedPosition) {
                it.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.white
                    )
                )
            }
            else {
                it.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.amber_500
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMathResponseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textView: TextView = binding.mathReponse
        val cardView: CardView = binding.mathReponseCard

        init {
            cardView.setOnClickListener {
                selectedPosition = absoluteAdapterPosition
                lambda(selectedPosition)
                notifyDataSetChanged()
            }
        }
    }

}