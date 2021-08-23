package com.felix.gotraining.data.widget

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.felix.gotraining.R
import com.felix.gotraining.data.model.Exercise

class ExerciseAdapter(
    private var exercises: List<Exercise>,
    var selectExercise: (exercise: Exercise) -> Unit,
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExerciseViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.exercise_item, parent, false)
    )

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exercises[position])
    }

    override fun getItemCount(): Int = exercises.size

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textFieldName: TextView = itemView.findViewById(R.id.txt_exercise_name)
        private val textFieldRange: TextView = itemView.findViewById(R.id.txt_serie)
        private val textFieldRepeat: TextView = itemView.findViewById(R.id.txt_repeat)

        fun bind(exercise: Exercise) {
            textFieldName.text = exercise.name
            itemView.setOnClickListener {
                if (exercise.selected) {
                    setAttributesToExercise(
                        exercise,
                        selected = false,
                        range = 0,
                        repeat = 0
                    )
                    resetViewSelected()
                    selectExercise.invoke(exercise)
                } else {
                    ViewDialog.showSelectDialog(itemView.context) { range, repeat ->
                        setAttributesToExercise(
                            exercise,
                            selected = true,
                            range,
                            repeat
                        )
                        selectView(range, repeat)
                        selectExercise.invoke(exercise)
                    }
                }
            }
        }

        private fun selectView(range: Int, repeat: Int) {
            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(R.attr.colorSecondary, typedValue, true)
            val color = if (typedValue.resourceId != 0) {
                typedValue.resourceId
            } else {
                typedValue.data
            }
            changeBackgroundColor(color)
            setRepeatRangeVisibility(viewsVisible = true)
            textFieldRange.text = "Série: $range"
            textFieldRepeat.text = "Repetições: $repeat"
        }

        private fun setRepeatRangeVisibility(viewsVisible: Boolean) {
            textFieldRange.isVisible = viewsVisible
            textFieldRepeat.isVisible = viewsVisible
        }

        private fun changeBackgroundColor(color: Int) {
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    color
                )
            )
        }

        private fun setAttributesToExercise(
            exercise: Exercise,
            selected: Boolean,
            range: Int,
            repeat: Int
        ) {
            exercise.selected = selected
            exercise.range = range
            exercise.repeat = repeat
        }

        private fun resetViewSelected() {
            changeBackgroundColor(color = android.R.color.transparent)
            setRepeatRangeVisibility(viewsVisible = false)
        }
    }
}