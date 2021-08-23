package com.felix.gotraining.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.felix.gotraining.data.entity.SelectedExerciseEntity

@Dao
interface SelectedInterfaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(selectedExerciseEntity: SelectedExerciseEntity)
}