package com.felix.gotraining.data.repository

import com.felix.gotraining.data.dao.SelectedInterfaceDao
import com.felix.gotraining.data.dao.TrainingDao
import com.felix.gotraining.data.entity.SelectedExerciseEntity
import com.felix.gotraining.data.entity.TrainingEntity
import com.felix.gotraining.data.model.Exercise
import com.felix.gotraining.data.model.Training
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TrainingRepository {
    suspend fun getAllTrainings(): List<Training>
    suspend fun saveTraining(training: Training)
    suspend fun getTraining(id: Long): Training
}

class TrainingRepositoryImpl(
    private val trainingDao: TrainingDao,
    private val selectedInterfaceDao: SelectedInterfaceDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TrainingRepository {

    override suspend fun getAllTrainings(): List<Training> = withContext(dispatcher) {
        val trainings = mutableListOf<Training>()
        val trainingsEntity = trainingDao.getTrainings()
        trainingsEntity.forEach { trainingEntity ->
            trainings.add(Training(id = trainingEntity.id, name = trainingEntity.name))
        }

        return@withContext trainings
    }

    override suspend fun saveTraining(training: Training) = withContext(dispatcher) {
        val trainingEntity = TrainingEntity(id = 0, name = training.name)
        val trainingEntityId = trainingDao.insert(trainingEntity)

        training.exercises.forEach { exercise ->
            val selectedExerciseEntity = SelectedExerciseEntity(
                name = exercise.name,
                number = exercise.number,
                range = exercise.range,
                repeat = exercise.repeat,
                trainingId = trainingEntityId
            )

            selectedInterfaceDao.insert(selectedExerciseEntity)
        }
    }

    override suspend fun getTraining(id: Long): Training = withContext(dispatcher) {
        val exercises = mutableListOf<Exercise>()
        val trainingEntity = trainingDao.getTrainingExercise(id)
        trainingEntity.exercises.forEach { selectedExerciseEntity ->
            exercises.add(
                Exercise(
                    id = selectedExerciseEntity.id,
                    name = selectedExerciseEntity.name,
                    number = selectedExerciseEntity.number,
                    range = selectedExerciseEntity.range,
                    repeat = selectedExerciseEntity.repeat
                )
            )
        }

        return@withContext Training(
            trainingEntity.trainingEntity.id,
            trainingEntity.trainingEntity.name,
            exercises
        )
    }

}