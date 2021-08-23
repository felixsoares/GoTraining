package com.felix.gotraining.data.repository

import com.felix.gotraining.data.dao.ExerciseDao
import com.felix.gotraining.data.dao.GroupDao
import com.felix.gotraining.data.entity.ExerciseEntity
import com.felix.gotraining.data.entity.GroupEntity
import com.felix.gotraining.data.model.Exercise
import com.felix.gotraining.data.model.GroupExercise
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GroupExerciseRepository {
    suspend fun saveGroupsExercise(json: String)
    suspend fun getGroupsExercise(): List<GroupExercise>
}

class GroupExerciseRepositoryImpl(
    private val groupDao: GroupDao,
    private val exerciseDao: ExerciseDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GroupExerciseRepository {

    override suspend fun saveGroupsExercise(json: String) = withContext(dispatcher) {
        val groupListType = object : TypeToken<List<GroupExercise>>() {}.type
        val groupsExercise: List<GroupExercise> = Gson().fromJson(json, groupListType)

        groupsExercise.forEach { groupExercise ->
            val groupEntity = GroupEntity(name = groupExercise.name)
            val groupEntityId = groupDao.insert(groupEntity)

            groupExercise.exercises.forEach { exercise ->
                val exerciseEntity = ExerciseEntity(
                    name = exercise.name,
                    number = exercise.number,
                    groupExerciseId = groupEntityId
                )
                exerciseDao.insert(exerciseEntity)
            }
        }
    }

    override suspend fun getGroupsExercise(): List<GroupExercise> = withContext(dispatcher) {
        val groupsExercise = mutableListOf<GroupExercise>()
        val groupsExerciseEntity = groupDao.getGroupsExercise()
        groupsExerciseEntity.forEach { groupExerciseEntity ->
            val exercises = mutableListOf<Exercise>()
            groupExerciseEntity.exercises.forEach { exerciseEntity ->
                val exercise = Exercise(
                    id = exerciseEntity.id,
                    name = exerciseEntity.name,
                    number = exerciseEntity.number,
                )
                exercises.add(exercise)
            }

            val groupExercise = GroupExercise(
                name = groupExerciseEntity.group.name,
                exercises = exercises
            )
            groupsExercise.add(groupExercise)
        }

        return@withContext groupsExercise
    }

}