package com.felix.gotraining.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training")
data class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)