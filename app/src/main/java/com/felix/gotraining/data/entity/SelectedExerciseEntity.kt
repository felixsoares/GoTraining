package com.felix.gotraining.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_exercise")
data class SelectedExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val number: String,
    var range: Int,
    var repeat: Int,
    val trainingId: Long
)