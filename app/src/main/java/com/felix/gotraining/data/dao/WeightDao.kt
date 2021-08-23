package com.felix.gotraining.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felix.gotraining.data.entity.WeightEntity

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weightEntity: WeightEntity)

    @Query("SELECT * FROM `weight` WHERE exerciseId = :idExercise")
    fun getWeights(idExercise: Long): List<WeightEntity>
}