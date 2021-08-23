package com.felix.gotraining.data.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felix.gotraining.R
import com.felix.gotraining.data.model.Weight

class WeightAdapter(
    private val weights: MutableList<Weight>
) : RecyclerView.Adapter<WeightAdapter.WeightViewHolder>() {

    private val maxValue: Int
        get() = weights.maxOfOrNull { weight -> weight.value } ?: 0

    fun updateWeights(newWeights: List<Weight>) {
        weights.clear()
        weights.addAll(newWeights)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeightViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.weight_item, parent, false)
    )

    override fun onBindViewHolder(holder: WeightViewHolder, position: Int) {
        holder.bind(weight = weights[position])
    }

    override fun getItemCount(): Int = weights.size

    inner class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewController = itemView.findViewById<View>(R.id.controller)
        private val viewBar = itemView.findViewById<View>(R.id.bar)
        private val textWeight = itemView.findViewById<TextView>(R.id.txt_weight)

        fun bind(weight: Weight) {
            val percent: Float = (weight.value.toFloat() / maxValue.toFloat())
            val controller: Float = 1 - percent

            val layoutParams = viewController.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = percent
            viewController.layoutParams = layoutParams

            val barLayoutParams = viewBar.layoutParams as LinearLayout.LayoutParams
            barLayoutParams.weight = controller
            viewBar.layoutParams = barLayoutParams

            textWeight.text = "${weight.value} Kg"
        }
    }
}