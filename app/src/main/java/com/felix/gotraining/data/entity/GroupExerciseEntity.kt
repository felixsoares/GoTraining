package com.felix.gotraining.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class GroupExerciseEntity(
    @Embedded
    val group: GroupEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "groupExerciseId"
    )
    val exercises: List<ExerciseEntity>
)