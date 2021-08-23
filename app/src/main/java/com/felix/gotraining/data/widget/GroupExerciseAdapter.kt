package com.felix.gotraining.data.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felix.gotraining.R
import com.felix.gotraining.data.model.Exercise
import com.felix.gotraining.data.model.GroupExercise

class GroupExerciseAdapter(
    private var groupsExercise: List<GroupExercise>,
    var selectExercise: (exercise: Exercise) -> Unit,
) : RecyclerView.Adapter<GroupExerciseAdapter.GroupExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GroupExerciseViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.group_of_exercise_item, parent, false)
    )

    override fun onBindViewHolder(holder: GroupExerciseViewHolder, position: Int) {
        holder.bind(groupsExercise[position])
    }

    override fun getItemCount(): Int = groupsExercise.size

    inner class GroupExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.txt_group_name)
        private val childRecyclerView: RecyclerView =
            itemView.findViewById(R.id.child_recycler_view)

        fun bind(groupExercise: GroupExercise) {
            textViewName.text = groupExercise.name
            val childAdapter = ExerciseAdapter(groupExercise.exercises, selectExercise)
            childRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            childRecyclerView.adapter = childAdapter
        }
    }
}