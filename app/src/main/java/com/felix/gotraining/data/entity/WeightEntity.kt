package com.felix.gotraining.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight")
data class WeightEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var value: Int,
    val exerciseId: Long
)