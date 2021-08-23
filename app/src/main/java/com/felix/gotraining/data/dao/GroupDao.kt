package com.felix.gotraining.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.felix.gotraining.data.entity.GroupEntity
import com.felix.gotraining.data.entity.GroupExerciseEntity

@Dao
interface GroupDao {
    @Query("SELECT * FROM `group`")
    fun getGroups(): LiveData<List<GroupEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(group: GroupEntity): Long

    @Transaction
    @Query("SELECT * FROM `group`")
    fun getGroupsExercise(): List<GroupExerciseEntity>
}