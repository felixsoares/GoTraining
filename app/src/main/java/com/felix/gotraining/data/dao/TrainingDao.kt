package com.felix.gotraining.data.dao

import androidx.room.*
import com.felix.gotraining.data.entity.TrainingEntity
import com.felix.gotraining.data.entity.TrainingSelectedExerciseEntity

@Dao
interface TrainingDao {
    @Query("SELECT * FROM `training`")
    fun getTrainings(): List<TrainingEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trainingEntity: TrainingEntity): Long

    @Transaction
    @Query("SELECT * FROM `training` WHERE id = :idExercise")
    fun getTrainingExercise(idExercise: Long): TrainingSelectedExerciseEntity
}