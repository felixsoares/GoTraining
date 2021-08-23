package com.felix.gotraining.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felix.gotraining.data.dao.*
import com.felix.gotraining.data.entity.*

@Database(
    entities = [
        GroupEntity::class, ExerciseEntity::class, TrainingEntity::class,
        SelectedExerciseEntity::class, WeightEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGroupDao(): GroupDao
    abstract fun getExerciseDao(): ExerciseDao
    abstract fun getTrainingDao(): TrainingDao
    abstract fun getSelectedExerciseDao(): SelectedInterfaceDao
    abstract fun getWeightDao(): WeightDao
}