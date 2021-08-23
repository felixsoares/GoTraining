package com.felix.gotraining.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingSelectedExerciseEntity(
    @Embedded
    val trainingEntity: TrainingEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "trainingId"
    )
    val exercises: List<SelectedExerciseEntity>
)