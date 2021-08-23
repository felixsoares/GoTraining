package com.felix.gotraining.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.gotraining.data.model.Exercise
import com.felix.gotraining.data.model.Weight
import com.felix.gotraining.data.repository.TrainingRepository
import com.felix.gotraining.data.repository.WeightRepository
import kotlinx.coroutines.launch

class TrainingViewModel(
    private val trainingRepository: TrainingRepository,
    private val weightRepository: WeightRepository
) : ViewModel() {

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise> = _exercise

    private val _weight = MutableLiveData<List<Weight>>()
    val weights: LiveData<List<Weight>> = _weight

    private val exercises = mutableListOf<Exercise>()
    private var position: Int = 0

    fun loadTraining(id: Long) {
        viewModelScope.launch {
            val training = trainingRepository.getTraining(id)
            val trainingExercises = training.exercises
            val exercise = trainingExercises[position]
            exercises.addAll(trainingExercises)
            _exercise.postValue(exercise)
            _weight.postValue(weightRepository.getWeights(exercise.id))
        }
    }

    fun loadNextExercise() {
        if (position + 1 < exercises.size) {
            position += 1
            loadExercise()
        }
    }

    fun loadPrevExercise() {
        if (position - 1 >= 0) {
            position -= 1
            loadExercise()
        }
    }

    private fun loadExercise() {
        viewModelScope.launch {
            val exercise = exercises[position]
            _exercise.postValue(exercise)
            _weight.postValue(weightRepository.getWeights(exercise.id))
        }
    }

    fun setWeight(weight: Int) {
        viewModelScope.launch {
            val exercise = exercises[position]
            weightRepository.saveWeight(weight, exercise.id)
            _weight.postValue(weightRepository.getWeights(exercise.id))
        }
    }
}