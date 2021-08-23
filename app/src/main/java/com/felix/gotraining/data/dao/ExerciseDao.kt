package com.felix.gotraining.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felix.gotraining.data.entity.ExerciseEntity

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM `exercise`")
    suspend fun getExercises(): List<ExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: ExerciseEntity)

}