package com.felix.gotraining.data.repository

import com.felix.gotraining.data.dao.WeightDao
import com.felix.gotraining.data.entity.WeightEntity
import com.felix.gotraining.data.model.Weight
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface WeightRepository {
    suspend fun saveWeight(weight: Int, exerciseId: Long)
    suspend fun getWeights(exerciseId: Long): List<Weight>
}

class WeightRepositoryImpl(
    private val weightDao: WeightDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeightRepository {

    override suspend fun saveWeight(weight: Int, exerciseId: Long) = withContext(dispatcher) {
        weightDao.insert(WeightEntity(value = weight, exerciseId = exerciseId))
    }

    override suspend fun getWeights(exerciseId: Long): List<Weight> = withContext(dispatcher) {
        val weights = mutableListOf<Weight>()
        val weightsEntity = weightDao.getWeights(exerciseId)
        weightsEntity.forEach { weightEntity ->
            weights.add(
                Weight(
                    id = weightEntity.id,
                    value = weightEntity.value
                )
            )
        }
        return@withContext weights
    }

}