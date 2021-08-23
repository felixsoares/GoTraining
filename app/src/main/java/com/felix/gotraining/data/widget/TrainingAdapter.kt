package com.felix.gotraining.data.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felix.gotraining.R
import com.felix.gotraining.data.model.Training

class TrainingAdapter(
    val onItemSelected: (training: Training) -> Unit
) : RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    private var trainings = mutableListOf<Training>()

    fun updateTrainings(newTrainings: List<Training>) {
        trainings.clear()
        trainings.addAll(newTrainings)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.training_item, parent, false)
    )

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(trainings[position])
    }

    override fun getItemCount(): Int = trainings.size

    inner class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTrainingName: TextView = itemView.findViewById(R.id.txt_training_name)
        fun bind(training: Training) {
            textTrainingName.text = training.name
            itemView.setOnClickListener {
                onItemSelected.invoke(training)
            }
        }
    }
}